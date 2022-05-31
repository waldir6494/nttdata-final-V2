package com.nttdata.app.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerCreate {
    private long id;
    private String name;
    private String surname;
    private String dni;
    private long type_id;
}
