package com.cognizant.transactionmicroservice.service;

import java.util.ArrayList;
import java.util.List;

import com.cognizant.transactionmicroservice.dto.CustomerDetails;
import com.cognizant.transactionmicroservice.dto.StatementDTO;
import com.cognizant.transactionmicroservice.model.TransactionModel;
import com.cognizant.transactionmicroservice.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionRecordServiceImpl implements TransactionRecordService {

    @Autowired
    private TransactionRepository repo;

    @Override
    public List<StatementDTO> getStatement(CustomerDetails customer) {
        
        List<StatementDTO> statement = new ArrayList<>();
        List<TransactionModel> transactions = repo.findByUserName(customer.getUserName());
        for(TransactionModel transaction:transactions){
            StatementDTO st = new StatementDTO(transaction.getTransactionDate(),transaction.getDescription(),transaction.getTransactiontype(),transaction.getAmount());
            statement.add(st);
        }
        return statement;   
        
    }
    
}
