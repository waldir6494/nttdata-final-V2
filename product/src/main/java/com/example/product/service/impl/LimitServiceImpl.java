package com.example.product.service.impl;

import com.example.product.model.Limit;
import com.example.product.service.ILimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LimitServiceImpl implements ILimitService {

    @Autowired
    public List<Limit> limits;

    @Override
    public Limit show(long id) {
        return this.limits.stream()
                .filter(limit -> limit.getId() == id)
                .findFirst()
                .get();
    }

    @Override
    public List<Limit> all() {
        return this.limits;
    }

    @Override
    public List<Limit> productFilter(long id) {
        return this.limits.stream().filter(limit -> limit.getProduct().getId() == id).collect(Collectors.toList());
    }
}
