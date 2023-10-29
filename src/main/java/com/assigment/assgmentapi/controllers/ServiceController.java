package com.assigment.assgmentapi.controllers;

import com.assigment.assgmentapi.handler.ResponseHandler;
import com.assigment.assgmentapi.models.ServicesEntity;
import com.assigment.assgmentapi.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceController {
    
    @Autowired
    private ServiceService service;
    
    @GetMapping("/services")
    public ResponseEntity<Object> findAll() {
        List<ServicesEntity> services = service.getAll();
        return ResponseHandler.generateResponse("Sukses", HttpStatus.OK, services);
    }
}
