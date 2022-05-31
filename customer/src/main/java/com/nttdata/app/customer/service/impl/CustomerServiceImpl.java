package com.nttdata.app.customer.service.impl;

import com.nttdata.app.customer.model.Customer;
import com.nttdata.app.customer.model.CustomerCreate;
import com.nttdata.app.customer.model.TypeCustomer;
import com.nttdata.app.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    public Flux<Customer> listCustomers;

    @Autowired
    public Flux<TypeCustomer> typeCustomer;
    public static int idCount = 1;

//    @Override
//    public Customer save(CustomerCreate customerCreate) {
//        //Random random = new Random();
//        //customer.setId(random.nextLong(100000 - 0) + 0);
//        long auxId = CustomerServiceImpl.idCount++;
//        //customer.setId(auxId);
//        Customer customerAux = new Customer(auxId, customerCreate.getName(),
//                customerCreate.getSurname(),
//                customerCreate.getDni(),
//                typeCustomer.stream().filter(typeCustomer1 -> typeCustomer1.getId() == customerCreate.getType_id())
//                        .findFirst()
//                        .get());
//        //this.customers.add(customer);
//        this.customers.add(customerAux);
//        return customerAux;
//    }
    @Override
    public Mono<Customer> save(CustomerCreate customerCreate) {
        //Random random = new Random();
        //customer.setId(random.nextLong(100000 - 0) + 0);
        long auxId = CustomerServiceImpl.idCount++;
        Mono<Customer> customerAux= typeCustomer.
                filter(typeCustomer1 ->typeCustomer1.getId() == customerCreate.getType_id()).
                next().
                map(typeCustomer-> {
                    Customer customer= new Customer(auxId, customerCreate.getName(), customerCreate.getSurname(), customerCreate.getDni(), typeCustomer);
                    this.listCustomers=listCustomers.concat(Flux.just(customer));
                    return customer;
                });
        //this.customers.add(customer);
//        this.customers.add(customerAux);
        return customerAux;
    }
//    @Override
//    public Customer show(long id) {
//        try {
//            return this.customers.stream()
//                    .filter(customer -> customer.getId() == id)
//                    .findFirst()
//                    .get();
//        } catch (Exception e) {
//            return new Customer();
//        }
//
//    }
    @Override
    public Mono<Customer> show(long id) {
        return this.listCustomers.filter(customer -> customer.getId() == id).next();

    }

    @Override
    public Flux<Customer> all() {
        return this.listCustomers;
    }

    @Override
    public Customer update() {
        // TODO Auto-generated method stub
        return null;
    }

//    @Override
//    public void delete(long id) {
//        this.customers.removeIf(customer -> customer.getId() == id);
//    }
}
