package com.assigment.assgmentapi.repositories;

import com.assigment.assgmentapi.models.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, String> {
    Optional<UsersEntity> findByEmail(String email);
}
