package com.nttdata.app.transaction.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionCreate {
    private Long typeTransactionId;
    private Float amount;
    private Long accountId;
}
