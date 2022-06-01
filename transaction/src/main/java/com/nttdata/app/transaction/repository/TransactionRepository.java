package com.nttdata.app.transaction.repository;

import com.nttdata.app.transaction.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
