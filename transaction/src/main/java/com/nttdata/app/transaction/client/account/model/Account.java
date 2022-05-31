package com.nttdata.app.transaction.client.account.model;

import com.nttdata.app.transaction.client.customer.model.Customer;
import com.nttdata.app.transaction.client.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Account {
    private long id;
    private Float balance;
    private Float credit;
    private Integer currentMovement;
    private Product product;
    private List<Customer> customers;
    private List<Customer> signatories;
}
