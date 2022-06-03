package com.nttdata.app.transaction.service.impl;

import com.nttdata.app.transaction.model.entity.TypeTransaction;
import com.nttdata.app.transaction.repository.TypeTransactionRepository;
import com.nttdata.app.transaction.service.ITypeTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeTransactionServiceImpl implements ITypeTransactionService {
    @Autowired
    TypeTransactionRepository typeTransactionRepository;

    @Override
    public TypeTransaction save(TypeTransaction typeTransaction) {
        return typeTransactionRepository.save(typeTransaction);
    }

    @Override
    public TypeTransaction show(Long id) {
        return typeTransactionRepository.findById(id).get();
    }

    @Override
    public List<TypeTransaction> all() {
        return typeTransactionRepository.findAll();
    }
}
