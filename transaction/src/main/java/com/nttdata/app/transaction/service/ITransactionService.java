package com.nttdata.app.transaction.service;

import com.nttdata.app.transaction.model.Transaction;
import com.nttdata.app.transaction.model.TransactionCreate;

import java.util.List;

public interface ITransactionService {

    Transaction deposit(TransactionCreate transactionCreate);

    Transaction withdrawal(TransactionCreate transactionCreate);

    Transaction creditPayment(TransactionCreate transactionCreate);

    Transaction useCredit(TransactionCreate transactionCreate);
    Transaction show(long id);
    List<Transaction> all();
}
