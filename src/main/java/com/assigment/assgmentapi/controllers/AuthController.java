package com.assigment.assgmentapi.controllers;

import com.assigment.assgmentapi.exception.UserException;
import com.assigment.assgmentapi.handler.ResponseHandler;
import com.assigment.assgmentapi.properties.AuthenticationRequest;
import com.assigment.assgmentapi.properties.RegisterRequest;
import com.assigment.assgmentapi.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    
    @Autowired
    private final AuthenticationService service;
    private static final String PATTERN = "\"^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$\"";
    @PostMapping("/register")
    public ResponseEntity<Object> register(
            @RequestBody RegisterRequest request
    ) {
        if(request.getEmail().matches(PATTERN)){
            throw new BadCredentialsException("Paramter email tidak sesuai format");
        }else if(request.getPassword().length() < 8) {
            throw new BadCredentialsException("Paramter password tidak sesuai format");
        }else{
            return  ResponseHandler.generateResponse("Registrasi berhasil, silahkan login", HttpStatus.OK, service.register(request));
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<Object> login (
            @RequestBody AuthenticationRequest request
    )throws UserException {
        if(request.getEmail().matches(PATTERN)){
            return  ResponseHandler.generateResponse("Paramter email tidak sesuai format", HttpStatus.OK, service.login(request));
        }else{
            return  ResponseHandler.generateResponse("Login sukses", HttpStatus.OK, service.login(request));
        }
    }
}
