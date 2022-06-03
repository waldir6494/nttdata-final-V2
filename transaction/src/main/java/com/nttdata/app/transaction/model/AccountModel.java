package com.nttdata.app.transaction.model;

import com.nttdata.app.transaction.client.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountModel {
    private long id;
    private Float balance;
    private Float credit;
    private Integer currentMovement;
    private Product product;
}
