package com.assigment.assgmentapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table(name = "transactions")
public class TransactionsEntity {
    @Id
    @GeneratedValue
    private int id;
    private String user;
    private String service_code;
    private String transaction_type;
    private Timestamp ts;
}
