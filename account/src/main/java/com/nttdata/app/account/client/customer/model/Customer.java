package com.nttdata.app.account.client.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    private Long id;
    private String name;
    private String surname;
    private String dni;
    private TypeCustomer type;
}
