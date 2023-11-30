package com.assigment.assgmentapi.controllers;

import com.assigment.assgmentapi.handler.ResponseHandler;
import com.assigment.assgmentapi.models.BannersEntity;
import com.assigment.assgmentapi.services.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class BannerController {
    @Autowired
    private BannerService service;
    
    @GetMapping("/banner")
    public ResponseEntity<Object> findAll(){
        List<BannersEntity> banners = service.getAll();
        return ResponseHandler.generateResponse("Sukses", HttpStatus.OK, banners);
    }

    @GetMapping("/banner/{id}")
    public ResponseEntity<Object> findBanner(@PathVariable("id") int id){
        return ResponseHandler.generateResponse("Sukses", HttpStatus.OK, service.getBanner(id));
    }

    @PostMapping("/banner/insert")
    public ResponseEntity<Object> create(@RequestBody BannersEntity bannersEntity){
        return ResponseHandler.generateResponse("Berhasil", HttpStatus.OK, service.insertBanner(bannersEntity));
    }

    @PutMapping("/banner/insert/image/{bannerId}")
    public ResponseEntity<Object> insertImage(@RequestParam(name = "file") MultipartFile file, @PathVariable("bannerId") int id){
        return ResponseHandler.generateResponse("Berhasil", HttpStatus.OK, service.uploadBannerImage(id,file));
    }
}
