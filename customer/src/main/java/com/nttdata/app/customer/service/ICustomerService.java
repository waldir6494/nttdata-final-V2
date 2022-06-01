package com.nttdata.app.customer.service;

import com.nttdata.app.customer.model.entity.Customer;
import com.nttdata.app.customer.model.CustomerCreate;

import java.util.List;

public interface ICustomerService {
    Customer save(CustomerCreate customer);
    Customer show(long id);
    List<Customer> all();
    Customer update();
    void delete(long id);
}
