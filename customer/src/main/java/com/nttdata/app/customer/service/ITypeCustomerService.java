package com.nttdata.app.customer.service;

import com.nttdata.app.customer.model.Customer;
import com.nttdata.app.customer.model.TypeCustomer;

import java.util.List;

public interface ITypeCustomerService {
    TypeCustomer show(long id);
    List<TypeCustomer> all();
}
