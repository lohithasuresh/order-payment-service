package com.scaler.ecommerce.repository;

import com.scaler.ecommerce.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
