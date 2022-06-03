package com.example.product.service.impl;

import com.example.product.model.TypeProduct;
import com.example.product.repository.TypeProductRepository;
import com.example.product.service.ITypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeProductServiceImpl implements ITypeProductService {

    @Autowired
    TypeProductRepository typeProductRepository;
    @Override
    public TypeProduct show(Long id) {
        return typeProductRepository.findById(id).get();
    }

    @Override
    public List<TypeProduct> all() {
        return typeProductRepository.findAll();
    }
}
