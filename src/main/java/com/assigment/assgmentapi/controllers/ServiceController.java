package com.assigment.assgmentapi.controllers;

import com.assigment.assgmentapi.models.BannersEntity;
import com.assigment.assgmentapi.models.ServicesEntity;
import com.assigment.assgmentapi.services.BannerService;
import com.assigment.assgmentapi.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceController {
    
    @Autowired
    private ServiceService service;
    
    @GetMapping("/services")
    public ResponseEntity<List<ServicesEntity>> findAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
}
