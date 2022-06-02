package com.nttdata.app.customer.service.impl;

import com.nttdata.app.customer.model.entity.Customer;
import com.nttdata.app.customer.model.CustomerCreate;
import com.nttdata.app.customer.model.entity.TypeCustomer;
import com.nttdata.app.customer.repository.CustomerRepository;
import com.nttdata.app.customer.repository.TypeCustomerRepository;
import com.nttdata.app.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService { ;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TypeCustomerRepository typeCustomerRepository;
    @Override
    public Customer save(CustomerCreate customer) {
        Customer newCustomer=Customer.builder().
                name(customer.getName()).
                surname(customer.getSurname()).
                dni(customer.getDni()).
                type(typeCustomerRepository.findById(customer.getType_id()).get())
                .build();
        return customerRepository.save(newCustomer);
    }

    @Override
    public Customer show(Long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public List<Customer> all() {
        return customerRepository.findAll();
    }

    @Override
    public Customer update() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}
