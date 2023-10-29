package com.assigment.assgmentapi.services;

import com.assigment.assgmentapi.models.UsersEntity;
import com.assigment.assgmentapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    
    @Autowired
    UserRepository repository;
    
    public UsersEntity findById(String email){
        return repository.findById(email).orElseThrow(null);
    }
    
    public UsersEntity update(UsersEntity users, String email){
        UsersEntity exsistingUser = repository.findById(email).orElse(null);
        exsistingUser.setFirstName(users.getFirstName());
        exsistingUser.setLastName(users.getLastName());
        return repository.save(exsistingUser);
    }
    
    public UsersEntity updateProfilePicture(UsersEntity users, String email){
        UsersEntity exsistingUser = repository.findById(email).orElse(null);
        exsistingUser.setProfile_image(users.getProfile_image());
        return repository.save(exsistingUser);
    }
}
