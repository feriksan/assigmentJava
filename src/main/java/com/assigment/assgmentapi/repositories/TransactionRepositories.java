package com.assigment.assgmentapi.repositories;

import com.assigment.assgmentapi.models.TransactionDetailsEntity;
import com.assigment.assgmentapi.models.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class TransactionRepositories {
    @Autowired
    JdbcTemplate template;
    
    public List<TransactionMapper> getAllTransaction(){
        List<TransactionMapper> transactionsEntityList = new ArrayList<>();
        transactionsEntityList.addAll(template.query("SELECT transactiondetails.`invoice_number`, transaction_type, transactiondetails.`total_amount`, services.`service_name`, ts FROM ((transactions\n" +
                "INNER JOIN transactiondetails ON transactions.`id` = transactiondetails.`transaction`)\n" +
                "INNER JOIN services ON transactions.`service_code` = services.`id`)", new BeanPropertyRowMapper<>(TransactionMapper.class)));
        return transactionsEntityList;
    }
    public int insertTransaction(TransactionDetailsEntity transactionsEntity, String user){
        String queryTransaction = "insert into transactions(user, service_code, transaction_type) values(?, ?, ?);";
        String queryDetails =  "insert into transactiondetails(transaction, service_name, invoice_number, total_amount) values(?, ?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(
                con -> {
                    PreparedStatement ps =
                            con.prepareStatement(queryTransaction, new String[]{"id"});
                    ps.setString(1, user);
                    ps.setString(2, transactionsEntity.getTransaction().getService_code());
                    ps.setString(3, transactionsEntity.getTransaction().getTransaction_type());
                    
                    return ps;
                },
        keyHolder);
        template.update(
                con -> {
                    PreparedStatement ps =
                            con.prepareStatement(queryDetails, new String[]{"id"});
                    ps.setInt(1, Objects.requireNonNull(keyHolder.getKey()).intValue());
                    ps.setString(2, transactionsEntity.getService_name());
                    ps.setString(3, transactionsEntity.getInvoice_number());
                    ps.setInt(4, transactionsEntity.getTotal_amount());
                    
                    return ps;
                },
                keyHolder);
        return balancing(transactionsEntity, user);
    }
    
    public int getBalance(String user){
        String getUserBalance = "select balance from users where email = ?";
        int balance = template.queryForObject(getUserBalance, new Object[]{user}, Integer.class);
        return balance;
    }
    public int balancing(TransactionDetailsEntity detailsEntity, String user){
        int balance = getBalance(user);
        String queryTopUp =  "update users set balance = ? where email = ?";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int newBalance;
        if(Objects.equals(detailsEntity.getTransaction().getTransaction_type(), "TOPUP")){
            newBalance = balance + detailsEntity.getTotal_amount();
        }else{
            newBalance = balance - detailsEntity.getTotal_amount();
        }
        template.update(
                con -> {
                    PreparedStatement ps =
                            con.prepareStatement(queryTopUp, new String[]{"id"});
                    ps.setInt(1, newBalance);
                    ps.setString(2, user);

                    return ps;
                },
                keyHolder);
        return newBalance;
    }
}
