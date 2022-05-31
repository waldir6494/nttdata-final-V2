package com.nttdata.app.account.client.product.model;

import com.nttdata.app.account.client.customer.model.TypeCustomer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Limit {
    long id;
    Product product;
    TypeCustomer typeCustomer;
    Boolean limitAccount;
    Integer maxAccount;
}
