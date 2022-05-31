package com.nttdata.app.transaction.client.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    private long id;
    private String name;
    private String surname;
    private String dni;
    private TypeCustomer type;
}
