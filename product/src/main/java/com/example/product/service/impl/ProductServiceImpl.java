package com.example.product.service.impl;
import com.example.product.model.Product;
import com.example.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    public List<Product> products;
    @Override
    public Product show(long id) {
        try {
            return this.products.stream()
                    .filter(customer -> customer.getId() == id)
                    .findFirst()
                    .get();
        } catch (Exception e) {
            return new Product();
        }
    }

    @Override
    public List all() {
        return this.products;
    }
}
