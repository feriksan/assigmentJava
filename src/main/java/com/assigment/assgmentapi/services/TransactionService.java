package com.assigment.assgmentapi.services;

import com.assigment.assgmentapi.models.TransactionDetailsEntity;
import com.assigment.assgmentapi.models.TransactionsEntity;
import com.assigment.assgmentapi.models.UsersEntity;
import com.assigment.assgmentapi.repositories.TransactionDetailRepository;
import com.assigment.assgmentapi.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository repository;
    @Autowired
    TransactionDetailRepository detailRepository;
    @Autowired
    CashflowService cashflowService;
    @Autowired
    UserService userService;
    @Autowired
    JdbcTemplate jdbc;
    
    public TransactionDetailsEntity transaction(TransactionDetailsEntity transactionDetails){
        TransactionDetailsEntity detailsEntity = detailRepository.save(transactionDetails);
        cashflowService.expense(detailsEntity.getTransaction().getUser(), detailsEntity.getTotal_amount());
        return detailsEntity;
    }
    
    public UsersEntity topUpBalance(TransactionDetailsEntity transactionDetails){
        TransactionDetailsEntity detailsEntity = detailRepository.save(transactionDetails);
        cashflowService.income(detailsEntity.getTransaction().getUser(), detailsEntity.getTotal_amount());
        UsersEntity cekBalance = userService.findById(detailsEntity.getTransaction().getUser());
        return cekBalance;
    }
    
    public List<TransactionsEntity> cekTransaction(){
        return repository.findAll();
    }
}
