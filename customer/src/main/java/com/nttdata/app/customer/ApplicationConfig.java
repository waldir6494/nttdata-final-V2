package com.nttdata.app.customer;

import com.nttdata.app.customer.model.entity.Customer;
import com.nttdata.app.customer.model.entity.TypeCustomer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ApplicationConfig {
    @Bean
    public List<Customer> listCustomer(){

        List<Customer> list = new ArrayList<>();
        //Customer customer1 = new Customer(1,"Waldir","Ortiz","45744477");
        //Customer customer2 = new Customer(2,"Pedro","Ramirez","46454411");

        //list.add(customer1);
        //list.add(customer2);
        return list;

    }

    @Bean
    public List<TypeCustomer> typeCustomer(){
        List<TypeCustomer> typeList = new ArrayList<>();
        typeList.add(new TypeCustomer(1L,"Personal"));
        typeList.add(new TypeCustomer(2L,"Empresarial"));
        return typeList;

    }
}
