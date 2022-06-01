package com.example.product.service.impl;

import com.example.product.model.Limit;
import com.example.product.repository.LimitRepository;
import com.example.product.service.ILimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LimitServiceImpl implements ILimitService  {
    @Autowired
    LimitRepository limitRepository;
    @Override
    public Limit show(Long id) {
        return limitRepository.findById(id).get();
    }

    @Override
    public List<Limit> all() {
        return limitRepository.findAll();
    }

    @Override
    public List<Limit> productFilter(Long id) {
        return limitRepository.findAll().
                stream().
                filter(limit -> limit.getProduct().getId()==id).collect(Collectors.toList());
    }

}
