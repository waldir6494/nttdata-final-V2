package com.nttdata.app.customer.service.impl;

import com.nttdata.app.customer.model.TypeCustomer;
import com.nttdata.app.customer.service.ITypeCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class TypeCustomerImpl implements ITypeCustomerService {


    @Autowired
    public Flux<TypeCustomer> typeCustomer;


    @Override
    public Mono<TypeCustomer> show(long id) {
        return this.typeCustomer.filter(typeCustomer -> typeCustomer.getId() == id).next();
    }

    @Override
    public Flux<TypeCustomer> all() {
        return this.typeCustomer;
    }
}
