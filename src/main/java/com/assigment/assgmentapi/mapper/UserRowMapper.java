package com.assigment.assgmentapi.mapper;

import com.assigment.assgmentapi.models.UsersEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UsersEntity> {
    
    @Override
    public UsersEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        UsersEntity users = new UsersEntity();
        users.setEmail(rs.getString("email"));
        users.setFirstName(rs.getString("first_name"));
        users.setLastName(rs.getString("last_name"));
        users.setPassword(rs.getString("password"));
        users.setStatus(rs.getInt("status"));
        users.setProfile_image(rs.getString("profile_image"));
        users.setBalance(rs.getInt("balance"));
        users.setRole(rs.getString("role"));
        
        return users;
    }
}
