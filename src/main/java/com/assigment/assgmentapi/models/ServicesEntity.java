package com.assigment.assgmentapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "services")
public class ServicesEntity {
    @Id
    @GeneratedValue
    private int id;
    private String service_code;
    private String service_name;
    private String service_icon;
    private int service_tariff;
}
