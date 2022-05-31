package com.nttdata.app.customer.service;

import com.nttdata.app.customer.model.Customer;
import com.nttdata.app.customer.model.CustomerCreate;
import com.nttdata.app.customer.model.TypeCustomer;

import java.util.List;

public interface ICustomerService {
    Customer save(CustomerCreate customer);
    Customer show(long id);
    List<Customer> all();
    Customer update();
    void delete(long id);
}
