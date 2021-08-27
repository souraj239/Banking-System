package com.cognizant.customermicroservice.service;

import com.cognizant.customermicroservice.dto.TransactionDTO;
import com.cognizant.customermicroservice.model.CustomerDetails;
import com.cognizant.customermicroservice.repository.CustomerRepository;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import com.cognizant.customermicroservice.exception.AccountNotFoundException;
import java.util.Optional;
import java.util.List;

@Service
@Slf4j
public class CustomerAccountServiceImpl implements CustomerAccountService {

    private CustomerRepository customerRepository;

    @Override
    public CustomerDetails viewAccount(String user) {
        log.info(user);
        List<CustomerDetails> customers = customerRepository.findAll();
        for(CustomerDetails customer : customers){
            if(customer.getUserName().equals(user)){
                return customer;
            }
        }
        CustomerDetails details=customerRepository.findById(user).get(); 
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
