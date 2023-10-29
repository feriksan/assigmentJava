package com.assigment.assgmentapi.services;

import com.assigment.assgmentapi.models.CashflowsEntity;
import com.assigment.assgmentapi.models.UsersEntity;
import com.assigment.assgmentapi.repositories.CashflowRepository;
import com.assigment.assgmentapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;

@Service
public class CashflowService {
    @Autowired
    CashflowRepository repository;
    @Autowired
    UserRepository userRepository;
    
    public void cashflow(CashflowsEntity cashflows){
        String query = "INSERT INRO cashflow(sender, recepient, value) VALUES(?, ?, ?)";
        repository.save(cashflows);
    }
    
    public void income(String email, Integer value){
        UsersEntity saldoNow = userRepository.findById(email).orElseThrow(null);
        saldoNow.setBalance(saldoNow.getBalance() + value);
        CashflowsEntity cashflowsData = new CashflowsEntity();
        cashflowsData.setRecepient(email);
        cashflowsData.setSender("SYSTEM");
        cashflowsData.setValue(value);
        cashflow(cashflowsData);
        userRepository.save(saldoNow);
    }
    
    public void expense(String email, Integer value){
        UsersEntity saldoNow = userRepository.findById(email).orElseThrow(null);
        saldoNow.setBalance(saldoNow.getBalance() - value);
        CashflowsEntity cashflowsData = new CashflowsEntity();
        cashflowsData.setRecepient("SYSTEM");
        cashflowsData.setSender(email);
        cashflowsData.setValue(value);
        cashflow(cashflowsData);
        userRepository.save(saldoNow);
    }
    
}
