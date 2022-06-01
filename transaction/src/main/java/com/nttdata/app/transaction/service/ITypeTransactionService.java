package com.nttdata.app.transaction.service;

import com.nttdata.app.transaction.model.entity.TypeTransaction;
import java.util.List;

public interface ITypeTransactionService {
    TypeTransaction save(TypeTransaction typeTransaction);
    TypeTransaction show(Long id);
    List<TypeTransaction> all();
}
