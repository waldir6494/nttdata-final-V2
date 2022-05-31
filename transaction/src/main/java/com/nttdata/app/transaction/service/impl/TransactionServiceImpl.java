package com.nttdata.app.transaction.service.impl;

import com.nttdata.app.transaction.client.account.AccountClient;
import com.nttdata.app.transaction.client.account.model.Account;
import com.nttdata.app.transaction.client.account.model.CreateAccount;
import com.nttdata.app.transaction.client.product.model.Product;
import com.nttdata.app.transaction.exceptions.RequirementFailedException;
import com.nttdata.app.transaction.model.Transaction;
import com.nttdata.app.transaction.model.TransactionCreate;
import com.nttdata.app.transaction.model.TypeTransaction;
import com.nttdata.app.transaction.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.function.Supplier;

public class TransactionServiceImpl implements ITransactionService {

    public static long idCount = 1;
    @Autowired
    public List<Transaction> transactions;

    @Autowired
    public List<TypeTransaction> typeTransactions;

    @Autowired
    AccountClient customerClient;

    @Override
    public Transaction deposit(TransactionCreate transactionCreate) {
        Long auxId = TransactionServiceImpl.idCount++;
        try{
            Account account = this.customerClient.getAccountFeign(transactionCreate.getAccountId());

            //if (this.verifyTypeAccount(account.getProduct())){
            //    throw new RequirementFailedException("Este tipo de cuenta no acepta esta operacion.");
            //}
            account.setBalance(transactionCreate.getAmount() + account.getBalance());
            account.setCurrentMovement(account.getCurrentMovement()+1);

            if(!this.verifyTypeAccount(account))
                throw new RequirementFailedException("Este tipo de cuenta no acepta esta operacion.");

            this.updateAccount(account, transactionCreate);

            Transaction transaction = new Transaction(
                    auxId,
                    this.typeTransactions.stream().filter(type -> type.getId().equals(transactionCreate.getTypeTransactionId())).findFirst()
                            .orElseThrow(() -> new RequirementFailedException("Este tipo de operacion no se encuentra registrado.")),
                    transactionCreate.getAmount(),
                    account);
            this.transactions.add(transaction);

            return transaction;
        } catch (RequirementFailedException | HttpClientErrorException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public Transaction withdrawal(TransactionCreate transactionCreate) {
        return null;
    }

    @Override
    public Transaction creditPayment(TransactionCreate transactionCreate) {
        return null;
    }

    @Override
    public Transaction useCredit(TransactionCreate transactionCreate) {
        return null;
    }

    @Override
    public Transaction show(long id) {
        return null;
    }

    @Override
    public List<Transaction> all() {
        return null;
    }

    //private boolean verifyTypeAccount(Account account){

        //return type.getTypeProduct().getIsCredit();
    //}

    private boolean verifyTypeAccount(Account account){
        return checkConditionals(()->account.getProduct().getTypeProduct().getIsCredit(), ()->account.getProduct().isHaveLimitMovement(),() -> account.getProduct().getMaxMovement() < account.getCurrentMovement());
    }

    private static boolean checkConditionals(Supplier<Boolean> typeAccount, Supplier<Boolean> haveMaxMovement, Supplier<Boolean> maxMovement) {
        return (typeAccount.get() && haveMaxMovement.get() && maxMovement.get()) ? true : false;
    }
    private void updateAccount(Account account, TransactionCreate transactionCreate) throws HttpClientErrorException {
        this.customerClient.putAccountFeign(
                new CreateAccount(null, null, account.getBalance(),null,account.getCurrentMovement()), transactionCreate.getAccountId());
    }
}
