package com.cognizant.customermicroservice.service;

import com.cognizant.customermicroservice.model.CustomerDetails;

import org.springframework.stereotype.Service;

import com.cognizant.customermicroservice.dto.TransactionDTO;

@Service
public interface CustomerAccountService {

    public CustomerDetails viewAccount(String user);

    public boolean withdrawAmount(TransactionDTO transaction);

    public boolean depositAmount(TransactionDTO transaction);


    
}
