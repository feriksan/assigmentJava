package com.assigment.assgmentapi.services;

import com.assigment.assgmentapi.models.TransactionDetailsEntity;
import com.assigment.assgmentapi.models.TransactionsEntity;
import com.assigment.assgmentapi.repositories.TransactionDetailRepository;
import com.assigment.assgmentapi.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
    public TransactionDetailsEntity transaction(TransactionDetailsEntity transactionDetails){
        if(Objects.equals(transactionDetails.getTransaction().getTransaction_type(), "TOP_UP")){
            cashflowService.income(transactionDetails.getTransaction().getUser(), transactionDetails.getTotal_amount());
        }else{
            cashflowService.expense(transactionDetails.getTransaction().getUser(), transactionDetails.getTotal_amount());
        }
        return detailRepository.save(transactionDetails);
    }
    
    public List<TransactionsEntity> cekTransaction(){
        return repository.findAll();
    }
}
