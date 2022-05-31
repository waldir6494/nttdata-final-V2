package com.example.product.service;
import com.example.product.model.Product;

import java.util.List;

public interface IProductService {
    Product show(long id);
    List<Product> all();
}
