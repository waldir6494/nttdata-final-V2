package com.nttdata.app.account.client.product.model;

import com.nttdata.app.account.client.customer.model.TypeCustomer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Limit {
    Long id;
    Product product;
    Integer typeCustomer;
    Boolean limitAccount;
    Integer maxAccount;
}
