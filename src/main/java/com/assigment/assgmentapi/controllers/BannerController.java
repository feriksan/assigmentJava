package com.assigment.assgmentapi.controllers;

import com.assigment.assgmentapi.models.BannersEntity;
import com.assigment.assgmentapi.services.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BannerController {
    @Autowired
    private BannerService service;
    
    @GetMapping("/banner")
    public ResponseEntity<List<BannersEntity>> findAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
}
