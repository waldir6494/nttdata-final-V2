package com.nttdata.app.customer.client.account.model;

import com.nttdata.app.customer.client.product.model.Product;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {
    private long id;
    private Float balance;
    private Float credit;
    private Integer currentMovement;
    private Integer customers;
    private Product product;
}
