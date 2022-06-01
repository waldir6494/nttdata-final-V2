package com.nttdata.app.transaction.repository;

import com.nttdata.app.transaction.model.entity.TypeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeTransactionRepository extends JpaRepository<TypeTransaction, Long> {
}
