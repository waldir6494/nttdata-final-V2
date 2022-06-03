package com.nttdata.app.account.model;

import com.nttdata.app.account.client.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AccountProductModel {
    private long id;
    private Float balance;
    private Float credit;
    private Integer currentMovement;
    private Integer customer;
    private Product product;
}
