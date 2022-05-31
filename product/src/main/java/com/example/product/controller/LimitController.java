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
    private ILimitService limitService;

    @GetMapping("/limit")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Limit> all() {

        return limitService.all();
    }

    @GetMapping("/limit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Limit show(@PathVariable long id) {
        return limitService.show(id);
    }

    @GetMapping("/limit/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Limit> productFilter(@PathVariable long id) {
        return limitService.productFilter(id);
    }
}
