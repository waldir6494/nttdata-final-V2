package com.nttdata.app.customer.repository;

import com.nttdata.app.customer.model.entity.TypeCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeCustomerRepository extends JpaRepository<TypeCustomer, Long> {
}
