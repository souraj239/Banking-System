package com.cognizant.transactionmicroservice.service;

import org.springframework.stereotype.Service;

import com.cognizant.transactionmicroservice.dto.CustomerDetails;
import com.cognizant.transactionmicroservice.dto.TransactionDTO;

@Service
public interface TransactionService {

    public boolean transferAmount(String token,CustomerDetails fromAc, TransactionDTO toAc);

    public boolean cashWithdraw(String token,TransactionDTO fromAc);

    public boolean depositCash(String token,TransactionDTO toAc);
    
}
