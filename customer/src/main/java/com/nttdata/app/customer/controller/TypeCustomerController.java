package com.nttdata.app.customer.controller;

import com.nttdata.app.customer.model.TypeCustomer;
import com.nttdata.app.customer.service.ITypeCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TypeCustomerController {
    @Autowired
    private ITypeCustomerService typeCustomerService;

    @GetMapping(value = {"/type/customer"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Flux<TypeCustomer> all() {
        return typeCustomerService.all();
    }

    @GetMapping(value = {"/type/customer/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Mono<TypeCustomer> show(@PathVariable long id) {
        return typeCustomerService.show(id);
    }
}
