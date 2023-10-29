package com.assigment.assgmentapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "banners")
public class BannersEntity {
    
    @Id
    @GeneratedValue
    private int id;
    private String banner_name;
    private String banner_image;
    private String description;
}
