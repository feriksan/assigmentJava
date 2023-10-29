package com.assigment.assgmentapi.repositories;

import com.assigment.assgmentapi.models.BannersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends JpaRepository<BannersEntity, Long> {
}
