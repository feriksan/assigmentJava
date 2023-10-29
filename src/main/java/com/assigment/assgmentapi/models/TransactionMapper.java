package com.assigment.assgmentapi.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class TransactionMapper implements Serializable {
    private String invoice_number;
    private String transaction_type;
    private int total_amount;
    private String service_name;
    private String ts;
}
