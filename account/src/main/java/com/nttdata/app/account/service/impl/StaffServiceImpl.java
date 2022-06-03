package com.nttdata.app.account.service.impl;

import com.nttdata.app.account.client.customer.CustomerClient;
import com.nttdata.app.account.client.customer.model.Customer;
import com.nttdata.app.account.model.CreateStaff;
import com.nttdata.app.account.model.entity.Account;
import com.nttdata.app.account.model.entity.Staff;
import com.nttdata.app.account.repository.AccountRepository;
import com.nttdata.app.account.repository.StaffRepository;
import com.nttdata.app.account.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements IStaffService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    StaffRepository staffRepository;

    @Autowired
    CustomerClient customerClient;
    @Override
    public Staff create(CreateStaff staff) {
        Account account =accountRepository.findById(Long.valueOf(staff.getAccount_id())).get();
        Customer customer= customerClient.getCustomerFeign(Long.valueOf(account.getCustomer()));
        if(customer.getType().getId()==2){

            Staff auxStaff=Staff.builder()
                    .name(staff.getName())
                    .surname(staff.getSurname())
                    .dni(staff.getDni())
                    .type(staff.getType())
                    .account(account)
                    .build();
            return staffRepository.save(auxStaff);
        }else {
            System.out.println("Solo para Clientes Empresariales");
            return null;
        }
    }

    @Override
    public List<Staff> all() {
        return staffRepository.findAll();
    }

    @Override
    public Staff show(Long id) {
        return staffRepository.findById(id).get();
    }
}
