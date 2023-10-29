package com.assigment.assgmentapi.services;

import com.assigment.assgmentapi.models.BannersEntity;
import com.assigment.assgmentapi.repositories.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerService {
    @Autowired
    private BannerRepository repository;
    
    public List<BannersEntity> getAll(){
        return repository.findAll();
    }
}
