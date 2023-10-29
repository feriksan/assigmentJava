package com.assigment.assgmentapi.controllers;

import com.assigment.assgmentapi.models.UsersEntity;
import com.assigment.assgmentapi.services.FileStorageService;
import com.assigment.assgmentapi.services.FileUploadService;
import com.assigment.assgmentapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
public class UserController {
    
    @Autowired
    UserService service;
    
    @Autowired
    FileUploadService uploadService;
    
    @GetMapping("profile")
    public ResponseEntity<UsersEntity> findById(Principal principal){
        return new ResponseEntity<>(service.findById(principal.getName()), HttpStatus.OK);
    }
    
    @GetMapping("balance")
    public ResponseEntity<Integer> getBalance(Principal principal){
        return new ResponseEntity<>(service.findById(principal.getName()).getBalance(), HttpStatus.OK);
    }
    
    @PutMapping("profile/update")
    public ResponseEntity<UsersEntity> update(@RequestBody UsersEntity users, Principal principal){
        return new ResponseEntity<>(service.update(users, principal.getName()), HttpStatus.OK);
    }
    
    @PutMapping("profile/image")
    public ResponseEntity<UsersEntity> updateImage(@RequestParam("file") MultipartFile file, Principal principal){
        var upload = uploadService.uploadFile(file);
        UsersEntity profile = new UsersEntity();
        profile.setProfile_image(upload.getFileDownloadUri());
        return new ResponseEntity<>(service.updateProfilePicture(profile, principal.getName()), HttpStatus.OK);
    }
}
