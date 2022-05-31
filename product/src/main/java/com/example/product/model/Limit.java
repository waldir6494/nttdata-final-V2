package com.example.product.model;

import com.example.product.client.customer.model.TypeCustomer;
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
