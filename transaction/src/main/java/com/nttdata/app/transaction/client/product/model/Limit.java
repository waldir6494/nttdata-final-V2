package com.nttdata.app.transaction.client.product.model;

import com.nttdata.app.transaction.client.customer.model.TypeCustomer;
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
