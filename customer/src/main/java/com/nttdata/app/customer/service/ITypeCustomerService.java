package com.nttdata.app.customer.service;

import com.nttdata.app.customer.model.Customer;
import com.nttdata.app.customer.model.TypeCustomer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ITypeCustomerService {
//    TypeCustomer show(long id);
    Mono<TypeCustomer> show(long id);
    Flux<TypeCustomer> all();
}
