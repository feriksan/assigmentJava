package com.assigment.assgmentapi.services;

import com.assigment.assgmentapi.models.UsersEntity;
import com.assigment.assgmentapi.repositories.UserRepositories;
import com.assigment.assgmentapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserRepositories userRepositories;
    
    public UsersEntity findById(String email){
        return userRepositories.getUserByUsername(email);
    }
    
    public UsersEntity update(UsersEntity users, String email){
        UsersEntity exsistingUser = userRepositories.getUserByUsername(email);
        exsistingUser.setFirstName(users.getFirstName());
        exsistingUser.setLastName(users.getLastName());
        return userRepositories.insertUser(exsistingUser);
    }
    
    public UsersEntity updateProfilePicture(UsersEntity users, String email){
        UsersEntity exsistingUser = userRepositories.getUserByUsername(email);
        exsistingUser.setProfile_image(users.getProfile_image());
        return userRepositories.updateProfilePict(exsistingUser, email);
    }
}
