package com.cognizant.customermicroservice.service;

import com.cognizant.customermicroservice.dto.TransactionDTO;
import com.cognizant.customermicroservice.model.CustomerDetails;
import com.cognizant.customermicroservice.repository.CustomerRepository;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class CustomerAccountServiceImpl implements CustomerAccountService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDetails viewAccount(String user) {
        CustomerDetails details=customerRepository.findById(user).get(); 
        log.debug(user);
        return details;     
    }

    @Override
    public boolean withdrawAmount(TransactionDTO transaction) {
        CustomerDetails customerDetails=this.viewAccount(transaction.getUserName());  
        if(customerDetails.getAccountBalance()>=transaction.getAmount()){
            customerDetails.setAccountBalance(customerDetails.getAccountBalance()-transaction.getAmount());
            customerRepository.save(customerDetails);
            return true;
        }
        else{
            return false;
        }
    }
    

    @Override
    public boolean depositAmount(TransactionDTO transaction) {
        CustomerDetails customerDetails=this.viewAccount(transaction.getUserName());   
        customerDetails.setAccountBalance(customerDetails.getAccountBalance()+transaction.getAmount());
        customerRepository.save(customerDetails);
        return true;
    }
    
}
