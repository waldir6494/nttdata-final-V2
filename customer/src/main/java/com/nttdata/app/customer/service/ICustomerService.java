package com.nttdata.app.customer.service;

import com.nttdata.app.customer.model.Customer;
import com.nttdata.app.customer.model.CustomerCreate;
import com.nttdata.app.customer.model.TypeCustomer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ICustomerService {
    Mono<Customer> save(CustomerCreate customer);
    Mono<Customer> show(long id);
    Flux<Customer> all();
    Customer update();
//    void delete(long id);
}
