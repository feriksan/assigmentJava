package com.assigment.assgmentapi.controllers;

import com.assigment.assgmentapi.models.BannersEntity;
import com.assigment.assgmentapi.models.TransactionDetailsEntity;
import com.assigment.assgmentapi.models.TransactionsEntity;
import com.assigment.assgmentapi.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
    
    @Autowired
    TransactionService service;
    
    @PostMapping("transaction")
    public ResponseEntity<TransactionDetailsEntity> postTransaction(@RequestBody TransactionDetailsEntity detailsEntity){
        return new ResponseEntity<>(service.transaction(detailsEntity), HttpStatus.OK);
    }
    
    @GetMapping("/transaction/all")
    public ResponseEntity<List<TransactionsEntity>> findAll(){
        return new ResponseEntity<>(service.cekTransaction(), HttpStatus.OK);
    }
}
