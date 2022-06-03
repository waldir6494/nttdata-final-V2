package com.example.product.service.impl;
import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import com.example.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> all(){
        return productRepository.findAll();
    }
    @Override
    public Product show(Long id){
        return productRepository.findById(id).get();
    }

}
