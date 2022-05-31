package com.nttdata.app.customer.controller;
import com.nttdata.app.customer.model.Customer;
import com.nttdata.app.customer.model.CustomerCreate;
import com.nttdata.app.customer.model.TypeCustomer;
import com.nttdata.app.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping(value = {"/customer"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Flux<Customer> all() {

        return customerService.all();
    }

    @GetMapping(value = {"/customer/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Mono<Customer> show(@PathVariable long id) {
        return customerService.show(id);
    }

    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Customer> create(@RequestBody CustomerCreate customerCreated) {
        Mono<Customer> customer= this.customerService.save(customerCreated);
        return customer;
    }

//    @DeleteMapping("customer/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable long id) {
//        this.customerService.delete(id);
//    }
}
