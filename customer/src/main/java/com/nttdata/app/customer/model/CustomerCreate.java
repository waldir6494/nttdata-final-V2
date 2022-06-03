package com.nttdata.app.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerCreate {
    private Long id;
    private String name;
    private String surname;
    private String dni;
    private Long type_id;
}
