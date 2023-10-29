package com.assigment.assgmentapi.repositories;

import com.assigment.assgmentapi.models.ServicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServicesEntity, Long> {
}
