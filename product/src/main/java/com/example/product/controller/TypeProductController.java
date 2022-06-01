package com.example.product.controller;

import com.example.product.model.TypeProduct;
import com.example.product.service.ITypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TypeProductController {
    @Autowired
    ITypeProductService  typeProductService;

    @GetMapping(value = {"/typeProduct"})
    @ResponseStatus(HttpStatus.OK)
    public List<TypeProduct> all(){
        return typeProductService.all();
    }

    @GetMapping(value ={"/typeProduct/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public TypeProduct show(@PathVariable Long id){
        return typeProductService.show(id);
    }
}
