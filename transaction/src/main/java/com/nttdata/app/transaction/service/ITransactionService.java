package com.nttdata.app.transaction.service;

import com.nttdata.app.transaction.model.TransactionModel;
import com.nttdata.app.transaction.model.TransactionCreate;
import com.nttdata.app.transaction.model.entity.Transaction;

import java.util.List;

public interface ITransactionService {

    Transaction deposit(TransactionCreate transactionCreate);

    TransactionModel withdrawal(TransactionCreate transactionCreate);

    TransactionModel creditPayment(TransactionCreate transactionCreate);

    TransactionModel useCredit(TransactionCreate transactionCreate);
    TransactionModel show(long id);
    List<TransactionModel> all();
}
