package com.nttdata.app.customer.service;

import com.nttdata.app.customer.model.entity.TypeCustomer;

import java.util.List;

public interface ITypeCustomerService {
    TypeCustomer show(long id);
    List<TypeCustomer> all();
}
