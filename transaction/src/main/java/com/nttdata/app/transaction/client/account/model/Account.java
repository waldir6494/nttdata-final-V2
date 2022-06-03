package com.nttdata.app.transaction.client.account.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Account {
    private long id;
    private Float balance;
    private Float credit;
    private Integer currentMovement;
    private Integer product;
    private Integer customers;
}
