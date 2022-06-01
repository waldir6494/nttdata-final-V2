package com.nttdata.app.transaction.model;

import com.nttdata.app.transaction.client.account.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionModel {
    private Long id;
    private TypeTransactionModel typeTransaction;
    private Float amount;
    private Account account;
}
