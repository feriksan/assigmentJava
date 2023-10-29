package com.assigment.assgmentapi.repositories;

import com.assigment.assgmentapi.mapper.UserRowMapper;
import com.assigment.assgmentapi.models.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;


@Repository
public class UserRepositories {
    
    @Autowired
    JdbcTemplate template;
    
    public UsersEntity getUserByUsername(String email){
        UsersEntity userData;
        userData = template.queryForObject("select * from users where email = ?;", new Object[] { email }, new UserRowMapper());
        return userData;
    }
    
    public UsersEntity insertUser(UsersEntity users){
        String queryInsertUser = "insert into users(email, first_name, last_name, password, role) values(?, ?, ?, ?, ?) ON DUPLICATE KEY\n" +
                "    UPDATE first_name=?, last_name=?;";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(
                con -> {
                    PreparedStatement ps =
                            con.prepareStatement(queryInsertUser, new String[]{"id"});
                    ps.setString(1, users.getEmail());
                    ps.setString(2, users.getFirstName());
                    ps.setString(3, users.getLastName());
                    ps.setString(4, users.getPassword());
                    ps.setString(5, users.getRole());
                    ps.setString(6, users.getFirstName());
                    ps.setString(7, users.getLastName());
                    return ps;
                },
                keyHolder);
        return users;
    }
    
    public UsersEntity updateProfilePict(UsersEntity users, String email){
        String queryUpdate =  "update users set profile_image = ? where email = ?";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(
                con -> {
                    PreparedStatement ps =
                            con.prepareStatement(queryUpdate, new String[]{"id"});
                    ps.setString(1, users.getProfile_image());
                    ps.setString(2, email);
                    
                    return ps;
                },
                keyHolder);
        return users;
    }
}
