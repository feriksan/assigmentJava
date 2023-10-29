package com.assigment.assgmentapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "cashflow")
public class CashflowsEntity {
    
    @Id
    @GeneratedValue
    private int id;
    private String sender;
    private String recepient;
    private int value;
    
}
