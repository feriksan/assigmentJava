package com.assigment.assgmentapi.mapper;

import com.assigment.assgmentapi.models.BannersEntity;
import com.assigment.assgmentapi.models.ServicesEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceRowMapper implements RowMapper<ServicesEntity> {
    @Override
    public ServicesEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        ServicesEntity service = new ServicesEntity();
        service.setService_name(rs.getString("service_name"));
        service.setService_code(rs.getString("service_code"));
        service.setService_icon(rs.getString("service_icon"));
        service.setService_tariff(rs.getInt("service_tariff"));
        return service;
    }
}
