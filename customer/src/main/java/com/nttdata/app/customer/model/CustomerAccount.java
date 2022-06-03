package com.nttdata.app.customer.model;

import com.nttdata.app.customer.client.account.model.Account;
import com.nttdata.app.customer.model.entity.TypeCustomer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerAccount {
    private Long id;
    private String name;
    private String surname;
    private String dni;
    private TypeCustomer type;
    private List<Account> accounts;
}
