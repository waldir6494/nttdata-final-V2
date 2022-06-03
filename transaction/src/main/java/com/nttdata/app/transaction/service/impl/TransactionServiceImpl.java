package com.nttdata.app.transaction.service.impl;

import com.nttdata.app.transaction.client.account.AccountClient;
import com.nttdata.app.transaction.client.account.model.Account;
import com.nttdata.app.transaction.client.account.model.CreateAccount;
import com.nttdata.app.transaction.exceptions.RequirementFailedException;
import com.nttdata.app.transaction.model.TransactionModel;
import com.nttdata.app.transaction.model.TransactionCreate;
import com.nttdata.app.transaction.model.entity.Transaction;
import com.nttdata.app.transaction.repository.TransactionRepository;
import com.nttdata.app.transaction.repository.TypeTransactionRepository;
import com.nttdata.app.transaction.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.logging.Logger;

@Service
public class TransactionServiceImpl implements ITransactionService {
    private final static Logger LOGGER =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final Predicate<Float> verifyWithdrawal = balance -> balance >= 0;
    private final BiPredicate<Float, Float> verifyCredit = (firstAmount, secondAmount) -> firstAmount <= secondAmount;

    @Autowired
    AccountClient customerClient;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TypeTransactionRepository typeTransactionRepository;

    @Override
    public Transaction deposit(TransactionCreate transactionCreate) {
        try{
            Account account = this.customerClient.getAccountFeign(transactionCreate.getAccountId());
            this.checkTypeAccount(() -> account.getProduct().getTypeProduct().getIsCredit());

            if(account.getProduct().getTypeProduct().getIsCredit())
                throw new RequirementFailedException("Este tipo de cuenta no acepta esta operacion o alcanzo el limite de movimientos.");

            LOGGER.info(() -> account.getProduct().isHaveLimitMovement()+ "");
            LOGGER.info((account.getCurrentMovement() < account.getProduct().getMaxMovement()) + "");
            LOGGER.info(this.checkConditionalsDebit(() -> account.getProduct().isHaveLimitMovement(), () -> account.getCurrentMovement() < account.getProduct().getMaxMovement()) +"");
            if(this.checkConditionalsDebit(() -> account.getProduct().isHaveLimitMovement(), () -> account.getCurrentMovement() < account.getProduct().getMaxMovement()))
                throw new RequirementFailedException("Este tipo de cuenta no acepta esta operacion o alcanzo el limite de movimientos.");

            account.setBalance(transactionCreate.getAmount() + account.getBalance());

            return this.saveAndUpdateTransaction(account, transactionCreate);

        } catch (RequirementFailedException | HttpClientErrorException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public Transaction withdrawal(TransactionCreate transactionCreate) {

        try{
            Account account = this.customerClient.getAccountFeign(transactionCreate.getAccountId());
            this.checkTypeAccount(() ->account.getProduct().getTypeProduct().getIsCredit());

            if(this.checkConditionalsDebit(() -> account.getProduct().isHaveLimitMovement(), () -> account.getCurrentMovement() < account.getProduct().getMaxMovement()))
                throw new RequirementFailedException("Este tipo de cuenta no acepta esta operacion o alcanzo el limite de movimientos.");

            if(!this.verifyWithdrawal.test(account.getBalance() - transactionCreate.getAmount()))
                throw new RequirementFailedException("No tienes fondos suficientes para realizar esta operacion.");

            account.setBalance(account.getBalance() - transactionCreate.getAmount());
            return this.saveAndUpdateTransaction(account, transactionCreate);
        } catch (RequirementFailedException | HttpClientErrorException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private Transaction saveAndUpdateTransaction (Account account, TransactionCreate transactionCreate) throws RequirementFailedException, HttpClientErrorException {
        account.setCurrentMovement(account.getCurrentMovement()+1);
        this.updateAccount(account, transactionCreate);
        Transaction transaction = new Transaction();
        transaction.setTypeTransaction(this.typeTransactionRepository.findAll().stream().filter(type -> type.getId().equals(transactionCreate.getTypeTransactionId())).findFirst()
                .orElseThrow(() -> new RequirementFailedException("Este tipo de operacion no se encuentra registrado.")));
        transaction.setAmount(transactionCreate.getAmount());
        transaction.setAccountId(account.getId());
        transaction.setDateTransaction(new Date());
        return this.transactionRepository.save(transaction);
    }

    @Override
    public Transaction paymentCredit(TransactionCreate transactionCreate) {
        try{
            Account account = this.customerClient.getAccountFeign(transactionCreate.getAccountId());
            this.checkTypeAccount(() -> !account.getProduct().getTypeProduct().getIsCredit());

            if(!this.verifyWithdrawal.test(account.getBalance() - transactionCreate.getAmount()))
                throw new RequirementFailedException("El monto ingresado supera su deuda de credito.");

            account.setBalance(account.getBalance() - transactionCreate.getAmount());

            return this.saveAndUpdateTransaction(account, transactionCreate);

        } catch (RequirementFailedException | HttpClientErrorException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public Transaction useCredit(TransactionCreate transactionCreate) {
        try{
            Account account = this.customerClient.getAccountFeign(transactionCreate.getAccountId());
            this.checkTypeAccount(() ->!account.getProduct().getTypeProduct().getIsCredit());

            if(!this.verifyCredit.test(account.getBalance() + transactionCreate.getAmount(), account.getCredit()))
                throw new RequirementFailedException("Usted no puede exceder su linea de credito.");

            account.setBalance(account.getBalance() + transactionCreate.getAmount());

            return this.saveAndUpdateTransaction(account, transactionCreate);

        } catch (RequirementFailedException | HttpClientErrorException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public Transaction show(Long id) {
        return this.transactionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Transaction> findByAccount(Long id) {
        return this.transactionRepository.findByAccountId(id);
    }

    @Override
    public List<Transaction> all() {
        return this.transactionRepository.findAll();
    }

    private void checkTypeAccount(Supplier<Boolean> isCredit) throws RequirementFailedException{
        if(isCredit.get())
            throw new RequirementFailedException("Este tipo de cuenta no acepta esta operacion.");
    }
    private boolean checkConditionalsDebit(Supplier<Boolean> haveMaxMovement, Supplier<Boolean> maxMovement) {
        return (haveMaxMovement.get() && maxMovement.get());
    }
    private void updateAccount(Account account, TransactionCreate transactionCreate) throws HttpClientErrorException {
        this.customerClient.putAccountFeign(
                new CreateAccount(null, null, account.getBalance(), account.getCredit(), account.getCurrentMovement()), transactionCreate.getAccountId());
    }
}
