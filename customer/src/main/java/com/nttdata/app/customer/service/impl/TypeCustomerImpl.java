package com.nttdata.app.customer.service.impl;

import com.nttdata.app.customer.model.entity.TypeCustomer;
import com.nttdata.app.customer.repository.TypeCustomerRepository;
import com.nttdata.app.customer.service.ITypeCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeCustomerImpl implements ITypeCustomerService {

    @Autowired
    TypeCustomerRepository typeCustomerRepository;

    @Override
    public TypeCustomer show(Long id) {
        return typeCustomerRepository.findById(id).get();
    }

    @Override
    public List<TypeCustomer> all() {
        return typeCustomerRepository.findAll();
    }
}
