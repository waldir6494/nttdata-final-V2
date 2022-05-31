package com.nttdata.app.account.client.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TypeProduct {
    long id;
    String name;
    Boolean isCredit;
}