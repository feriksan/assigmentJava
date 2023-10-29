package com.assigment.assgmentapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Table(name = "transactiondetails")
public class TransactionDetailsEntity {
    
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transaction", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TransactionsEntity transaction;
    private String service_name;
    private String invoice_number;
    private int total_amount;
}
