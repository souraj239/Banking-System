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


     /**
     * This method gets the details the account.
     * @param user
     * @return response
     */
    @Override
    public CustomerDetails viewAccount(String user) {
        CustomerDetails details=customerRepository.findById(user).get(); 
        log.debug(user);
        return details;     

    }
      /**
     * This method withdraw money from account.
     * @param transaction
     * @return response.
     */
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
    
     /**
     * This method deposit money to the account.
     * @param transaction
     * @return boolean
     */
    @Override
    public boolean depositAmount(TransactionDTO transaction) {
        if(transaction.getAmount()>0){
        CustomerDetails customerDetails=this.viewAccount(transaction.getUserName());   
        customerDetails.setAccountBalance(customerDetails.getAccountBalance()+transaction.getAmount());
        customerRepository.save(customerDetails);
        return true;
        }else{
            return false;
        }
    }
    
}
