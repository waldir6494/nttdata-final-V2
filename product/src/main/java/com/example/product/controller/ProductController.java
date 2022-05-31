package com.example.product.controller;

import com.example.product.model.Product;
import com.example.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping(value = {"/product"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Product> all() {
        return productService.all();
    }

    @GetMapping(value = {"/product/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Product show(@PathVariable long id) {
        return productService.show(id);
    }
}
