package com.assigment.assgmentapi.repositories;

import com.assigment.assgmentapi.models.CashflowsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashflowRepository extends JpaRepository<CashflowsEntity, Long> {
    
    @Query(value = "SELECT tb_icon.id, tb_icon.jabatan, tb_icon.wilayahKerja, tb_icon.username, tb_icon.`email`, tb_icon.`namaLengkap`, tb_icon.`noHP` FROM tb_icon LEFT JOIN authorities ON authorities.`username` = tb_icon.`username` WHERE authorities.`authority` = 'ROLE_ASSET' GROUP BY tb_icon.`username`\n", nativeQuery = true)
    List<CashflowsEntity> getCashflow();
}
