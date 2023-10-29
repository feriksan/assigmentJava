package com.assigment.assgmentapi.controllers;

import com.assigment.assgmentapi.handler.ResponseHandler;
import com.assigment.assgmentapi.models.BannersEntity;
import com.assigment.assgmentapi.services.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BannerController {
    @Autowired
    private BannerService service;
    
    @GetMapping("/banner")
    public ResponseEntity<Object> findAll(){
        List<BannersEntity> banners = service.getAll();
        return ResponseHandler.generateResponse("Sukses", HttpStatus.OK, banners);
    }
}
