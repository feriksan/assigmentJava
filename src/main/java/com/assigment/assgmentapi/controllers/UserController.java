package com.assigment.assgmentapi.controllers;

import com.assigment.assgmentapi.handler.ResponseHandler;
import com.assigment.assgmentapi.models.UsersEntity;
import com.assigment.assgmentapi.repositories.TransactionRepositories;
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
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    
    @Autowired
    UserService service;
    
    @Autowired
    FileUploadService uploadService;
    
    @Autowired
    TransactionRepositories transactionRepositories;
    
    
    
    @GetMapping("profile")
    public ResponseEntity<Object> findById(Principal principal){
        UsersEntity data = service.findById(principal.getName());
        Map<String, Object> map = new HashMap<>();
        map.put("email", data.getEmail());
        map.put("first_name", data.getFirstName());
        map.put("last_name", data.getLastName());
        map.put("profile_image", data.getProfile_image());
        return ResponseHandler.generateResponse("Sukses", HttpStatus.OK, map);
    }
    
    @GetMapping("balance")
    public ResponseEntity<Object> getBalance(Principal principal){
        int balance = service.findById(principal.getName()).getBalance();
        Map<String, Object> map = new HashMap<>();
        map.put("balance", balance);
        return ResponseHandler.generateResponse("Get Balance Berhasil", HttpStatus.OK, map);
    }
    
    @PutMapping("profile/update")
    public ResponseEntity<Object> update(@RequestBody UsersEntity users, Principal principal){
        UsersEntity data = service.update(users, principal.getName());
        Map<String, Object> map = new HashMap<>();
        map.put("email", data.getEmail());
        map.put("first_name", data.getFirstName());
        map.put("last_name", data.getLastName());
        map.put("profile_image", data.getProfile_image());
        return ResponseHandler.generateResponse("Update Profile Berhasil", HttpStatus.OK, map);
    }
    
    @PutMapping("profile/image")
    public ResponseEntity<Object> updateImage(@RequestParam("file") MultipartFile file, Principal principal){
        var upload = uploadService.uploadFile(file);
        UsersEntity profile = new UsersEntity();
        profile.setProfile_image(upload.getFileDownloadUri());
        UsersEntity updatedUser = service.updateProfilePicture(profile, principal.getName());
        Map<String, Object> map = new HashMap<>();
        map.put("email", updatedUser.getEmail());
        map.put("first_name", updatedUser.getFirstName());
        map.put("last_name", updatedUser.getLastName());
        map.put("profile_image", updatedUser.getProfile_image());
        return ResponseHandler.generateResponse("Update Profile Image Berhasil", HttpStatus.OK, map);
    }
}
