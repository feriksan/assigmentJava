package com.assigment.assgmentapi.repositories;

import com.assigment.assgmentapi.models.ServicesEntity;
import com.assigment.assgmentapi.models.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetailsEntity, Long> {
}
