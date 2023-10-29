package com.assigment.assgmentapi.repositories;

import com.assigment.assgmentapi.models.TransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionsEntity, Long> {
}
