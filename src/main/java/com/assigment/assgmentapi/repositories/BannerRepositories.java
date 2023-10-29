package com.assigment.assgmentapi.repositories;

import com.assigment.assgmentapi.mapper.BannerRowMapper;
import com.assigment.assgmentapi.models.BannersEntity;
import com.assigment.assgmentapi.models.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BannerRepositories {
    @Autowired
    JdbcTemplate template;
    public List<BannersEntity> getAllBanner(){
        List<BannersEntity> bannerRowMapperList = new ArrayList<>();
        bannerRowMapperList.addAll(template.query("SELECT * from banners", new BannerRowMapper()));
        return bannerRowMapperList;
    }
}
