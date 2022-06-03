package com.example.product.controller;

import com.example.product.model.Limit;
import com.example.product.service.ILimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LimitController {

    @Autowired
    ILimitService limitService;

    @GetMapping("/limit")
    @ResponseStatus(HttpStatus.OK)
    public List<Limit> all(){
        return limitService.all();
    }
    @GetMapping("/limit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Limit show(@PathVariable Long id){
        return limitService.show(id);
    }

    @GetMapping("/limit/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Limit> getLimitByProduct(@PathVariable Long id){
        return  limitService.productFilter(id);
    }

}
