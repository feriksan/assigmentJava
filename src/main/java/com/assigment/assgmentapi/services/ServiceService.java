package com.assigment.assgmentapi.services;

import com.assigment.assgmentapi.models.ServicesEntity;
import com.assigment.assgmentapi.repositories.ServiceRepositories;
import com.assigment.assgmentapi.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {
    
    @Autowired
    private ServiceRepositories serviceRepositories;
    
    public List<ServicesEntity> getAll(){
        return serviceRepositories.getAllService();
    }
}
