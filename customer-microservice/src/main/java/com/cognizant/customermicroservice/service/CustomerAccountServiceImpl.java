package com.cognizant.customermicroservice.service;

import com.cognizant.customermicroservice.dto.TransactionDTO;
import com.cognizant.customermicroservice.model.CustomerDetails;
import com.cognizant.customermicroservice.repository.CustomerRepository;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import com.cognizant.customermicroservice.exception.AccountNotFoundException;
import java.util.Optional;

@Service
@Slf4j
public class CustomerAccountServiceImpl implements CustomerAccountService {

    private CustomerRepository customerRepository;

    @Override
    public CustomerDetails viewAccount(String user) {
        log.info(user);
        CustomerDetails details=customerRepository.findById(user).get(); 
        return details;     
    }

    @Override
    public boolean withdrawAmount(TransactionDTO transaction) {
        Optional<CustomerDetails> details = customerRepository.findById(transaction.getUserName());   
        if(details.isPresent()) {
            CustomerDetails customerDetails = details.get();
            if(customerDetails.getAccountBalance()>=transaction.getAmount()){
                customerDetails.setAccountBalance(customerDetails.getAccountBalance()-transaction.getAmount());
                customerRepository.save(customerDetails);
                return true;
            }
            else{
                return false;
            }
        }
        else{
            throw new AccountNotFoundException("Account Not Found");
        }
    }

    @Override
    public boolean depositAmount(TransactionDTO transaction) {
        Optional<CustomerDetails> details = customerRepository.findById(transaction.getUserName());   
        if(details.isPresent()) {
            CustomerDetails customerDetails = details.get();
            customerDetails.setAccountBalance(customerDetails.getAccountBalance()+transaction.getAmount());
            customerRepository.save(customerDetails);
            return true;
        }
        else{
            return false;
        }
    }
    
}
