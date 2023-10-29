package com.assigment.assgmentapi.repositories;

import com.assigment.assgmentapi.mapper.BannerRowMapper;
import com.assigment.assgmentapi.mapper.ServiceRowMapper;
import com.assigment.assgmentapi.models.BannersEntity;
import com.assigment.assgmentapi.models.ServicesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ServiceRepositories {
    @Autowired
    JdbcTemplate template;
    public List<ServicesEntity> getAllService(){
        List<ServicesEntity> serviceRowMapperList = new ArrayList<>();
        serviceRowMapperList.addAll(template.query("SELECT * from services", new ServiceRowMapper()));
        return serviceRowMapperList;
    }
}
