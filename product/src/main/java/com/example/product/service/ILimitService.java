package com.example.product.service;

import com.example.product.model.Limit;

import java.util.List;

public interface ILimitService {
    Limit show(long id);
    List<Limit> all();
    List<Limit> productFilter(long id);
}
