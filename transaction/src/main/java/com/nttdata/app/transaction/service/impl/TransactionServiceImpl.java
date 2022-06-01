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

import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;

@Service
public class TransactionServiceImpl implements ITransactionService {
    private final static Logger LOGGER =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

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
            if(!this.verifyTypeAccount(account))
                throw new RequirementFailedException("Este tipo de cuenta no acepta esta operacion o alcanzo el limite de movimientos.");

            account.setBalance(transactionCreate.getAmount() + account.getBalance());
            account.setCurrentMovement(account.getCurrentMovement()+1);

            this.updateAccount(account, transactionCreate);
            Transaction transaction = new Transaction();
            transaction.setTypeTransaction(this.typeTransactionRepository.findAll().stream().filter(type -> type.getId().equals(transactionCreate.getTypeTransactionId())).findFirst()
                    .orElseThrow(() -> new RequirementFailedException("Este tipo de operacion no se encuentra registrado.")));
            transaction.setAmount(transactionCreate.getAmount());
            transaction.setAccountId(account.getId());
            return this.transactionRepository.save(transaction);

        } catch (RequirementFailedException | HttpClientErrorException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public TransactionModel withdrawal(TransactionCreate transactionCreate) {
        return null;
    }

    @Override
    public TransactionModel creditPayment(TransactionCreate transactionCreate) {
        return null;
    }

    @Override
    public TransactionModel useCredit(TransactionCreate transactionCreate) {
        return null;
    }

    @Override
    public TransactionModel show(long id) {
        return null;
    }

    @Override
    public List<TransactionModel> all() {
        return null;
    }

    private boolean verifyTypeAccount(Account account){
        return checkConditionals(()->account.getProduct().getTypeProduct().getIsCredit(), ()->account.getProduct().isHaveLimitMovement(),() -> account.getCurrentMovement() < account.getProduct().getMaxMovement() );
    }

    private static boolean checkConditionals(Supplier<Boolean> typeAccount, Supplier<Boolean> haveMaxMovement, Supplier<Boolean> maxMovement) {
        return (!typeAccount.get() && haveMaxMovement.get() && maxMovement.get());
    }
    private void updateAccount(Account account, TransactionCreate transactionCreate) throws HttpClientErrorException {
        this.customerClient.putAccountFeign(
                new CreateAccount(null, null, account.getBalance(),null,account.getCurrentMovement()), transactionCreate.getAccountId());
    }
}
