package com.nttdata.app.transaction.service;

import com.nttdata.app.transaction.model.TransactionModel;
import com.nttdata.app.transaction.model.TransactionCreate;
import com.nttdata.app.transaction.model.entity.Transaction;

import java.util.List;

public interface ITransactionService {

    Transaction deposit(TransactionCreate transactionCreate);

    Transaction withdrawal(TransactionCreate transactionCreate);

    TransactionModel paymentCredit(TransactionCreate transactionCreate);

    TransactionModel useCredit(TransactionCreate transactionCreate);
    Transaction show(Long id);

    List<Transaction> findByAccount(Long id);
    List<Transaction> all();
}
