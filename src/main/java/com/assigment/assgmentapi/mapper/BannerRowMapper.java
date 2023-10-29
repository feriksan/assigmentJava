package com.assigment.assgmentapi.mapper;

import com.assigment.assgmentapi.models.BannersEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BannerRowMapper implements RowMapper<BannersEntity> {
    @Override
    public BannersEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        BannersEntity banners = new BannersEntity();
        banners.setBanner_name(rs.getString("banner_name"));
        banners.setBanner_image(rs.getString("banner_image"));
        banners.setDescription(rs.getString("description"));
        return banners;
    }
}
