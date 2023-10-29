package com.assigment.assgmentapi.controllers;

import com.assigment.assgmentapi.properties.AuthenticationRequest;
import com.assigment.assgmentapi.properties.RegisterRequest;
import com.assigment.assgmentapi.response.AuthenticationResponse;
import com.assigment.assgmentapi.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    
    @Autowired
    private final AuthenticationService service;
    
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return  ResponseEntity.ok(service.register(request));
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    ) {
        return  ResponseEntity.ok(service.login(request));
    }
}
