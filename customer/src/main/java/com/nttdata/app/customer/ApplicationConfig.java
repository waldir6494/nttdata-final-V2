package com.nttdata.app.customer;

import com.nttdata.app.customer.model.Customer;
import com.nttdata.app.customer.model.TypeCustomer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ApplicationConfig {
    @Bean
    public Flux<Customer> listCustomer(){

        List<Customer> list = new ArrayList<>();
        //Customer customer1 = new Customer(1,"Waldir","Ortiz","45744477");
        //Customer customer2 = new Customer(2,"Pedro","Ramirez","46454411");

        //list.add(customer1);
        //list.add(customer2);

        Flux<Customer> listFlux= Flux.fromIterable(list);
        return listFlux;

    }

//    @Bean
//    public List<TypeCustomer> typeCustomer(){
//        List<TypeCustomer> typeList = new ArrayList<>();
//        typeList.add(new TypeCustomer(1,"Personal"));
//        typeList.add(new TypeCustomer(2,"Empresarial"));
//        return typeList;
//
//    }
    @Bean
    public Flux<TypeCustomer> typeCustomer(){
        List<TypeCustomer> typeList = new ArrayList<>();
        typeList.add(new TypeCustomer(1,"Personal"));
        typeList.add(new TypeCustomer(2,"Empresarial"));
        Flux<TypeCustomer> typeListFlux = Flux.fromIterable(typeList);
        return typeListFlux;

    }
}