package com.assigment.assgmentapi.controllers;

import com.assigment.assgmentapi.handler.ResponseHandler;
import com.assigment.assgmentapi.models.*;
import com.assigment.assgmentapi.repositories.TransactionRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TransactionController {
    
    @Autowired
    TransactionRepositories transactionRepositories;
    
    @PostMapping("transaction")
    public ResponseEntity<Object> postTransaction(@RequestBody TransactionDetailsEntity detailsEntity, Principal principal){
        transactionRepositories.insertTransaction(detailsEntity, principal.getName());
        Map<String, Object> map = new HashMap<>();
            map.put("invoice_number", detailsEntity.getInvoice_number());
            map.put("service_code", detailsEntity.getTransaction().getService_code());
            map.put("service_name", detailsEntity.getService_name());
            map.put("transaction_type", detailsEntity.getTransaction().getTransaction_type());
            map.put("total_amount", detailsEntity.getTotal_amount());
            map.put("created_on", detailsEntity.getTransaction().getTs());
        return ResponseHandler.generateResponse("Transaksi Berhasil", HttpStatus.OK, map);
    }
    
    @PostMapping("topup")
    public ResponseEntity<Object> cekBalance(@RequestBody TransactionDetailsEntity detailsEntity, Principal principal){
        TransactionsEntity transactions = new TransactionsEntity();
        Instant instant = Instant.now();
        long timeStampMillis = instant.toEpochMilli();
        transactions.setTransaction_type("TOPUP");
        transactions.setUser(principal.getName());
        transactions.setService_code("1");
        detailsEntity.setTransaction(transactions);
        detailsEntity.setInvoice_number("INV" + timeStampMillis + "112");
        detailsEntity.setService_name("TOP UP");
        return ResponseHandler.generateResponse("Top up Balance Berhasil", HttpStatus.OK, transactionRepositories.insertTransaction(detailsEntity, principal.getName()));
    }
    
    
    @GetMapping("/transaction/history")
    public ResponseEntity<Object> findAll(){
        List<TransactionMapper> transaction = transactionRepositories.getAllTransaction();
        return ResponseHandler.generateResponse("Get History Berhasil", HttpStatus.OK, transaction);
    }
}
