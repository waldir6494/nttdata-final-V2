package com.example.product.service;

import com.example.product.model.TypeProduct;

import java.util.List;

public interface ITypeProductService {
    TypeProduct show(Long id);
    List<TypeProduct> all();
}
