package com.assigment.assgmentapi.services;

import com.assigment.assgmentapi.exception.UserException;
import com.assigment.assgmentapi.jwt.JwtService;
import com.assigment.assgmentapi.models.UsersEntity;
import com.assigment.assgmentapi.properties.AuthenticationRequest;
import com.assigment.assgmentapi.properties.RegisterRequest;
import com.assigment.assgmentapi.repositories.UserRepositories;
import com.assigment.assgmentapi.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepositories userRepositories;
    
    public AuthenticationResponse register(RegisterRequest request) {
        var users = UsersEntity.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepositories.insertUser(users);
        var jwtToken = jwtService.generateToken(users);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    
    public AuthenticationResponse login(AuthenticationRequest request) throws UserException {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        }catch (BadCredentialsException e){
            throw new UserException("Username atau password salah");
        }
        var user = userRepositories.getUserByUsername(request.getEmail());
        
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
