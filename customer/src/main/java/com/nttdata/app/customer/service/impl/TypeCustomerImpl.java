package com.nttdata.app.customer.service.impl;

import com.nttdata.app.customer.model.TypeCustomer;
import com.nttdata.app.customer.service.ITypeCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeCustomerImpl implements ITypeCustomerService {

    @Autowired
    public List<TypeCustomer> typeCustomer;

    @Override
    public TypeCustomer show(long id) {
        return this.typeCustomer.stream().filter(typeCustomer -> typeCustomer.getId() == id).findFirst().get();
    }

    @Override
    public List<TypeCustomer> all() {
        return this.typeCustomer;
    }
}
