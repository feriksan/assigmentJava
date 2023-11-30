package com.assigment.assgmentapi.repositories;

import com.assigment.assgmentapi.mapper.BannerRowMapper;
import com.assigment.assgmentapi.mapper.UserRowMapper;
import com.assigment.assgmentapi.models.BannersEntity;
import com.assigment.assgmentapi.models.TransactionDetailsEntity;
import com.assigment.assgmentapi.models.TransactionMapper;
import com.assigment.assgmentapi.models.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class BannerRepositories {
    @Autowired
    JdbcTemplate template;

    public List<BannersEntity> getAllBanner(){
        List<BannersEntity> bannerRowMapperList = new ArrayList<>();
        bannerRowMapperList.addAll(template.query("SELECT * from banners", new BannerRowMapper()));
        return bannerRowMapperList;
    }

    public BannersEntity getBanner(int id){
        BannersEntity bannersData;
        bannersData = template.queryForObject("select * from banners where id = ?;", new Object[] { id }, new BannerRowMapper());
        return bannersData;
    }

    public String insertBanner(BannersEntity bannerEntity){
        String queryBanner = "insert into banners(banner_name, banner_image, description) values(?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(
                con -> {
                    PreparedStatement ps =
                            con.prepareStatement(queryBanner, new String[]{"id"});
                    ps.setString(1, bannerEntity.getBanner_name());
                    ps.setString(2, bannerEntity.getBanner_image());
                    ps.setString(3, bannerEntity.getDescription());

                    return ps;
                },
                keyHolder);
        return "Data Berhasil Di Input";
    }

    public int updateBannerPict(int bannerId, String filename){
        String queryUpdate =  "update banners set banner_image = ? where id = ?";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(
                con -> {
                    PreparedStatement ps =
                            con.prepareStatement(queryUpdate, new String[]{"id"});
                    ps.setString(1, filename);
                    ps.setInt(2, bannerId);
                    return ps;
                },
                keyHolder);
        return bannerId;
    }
}
