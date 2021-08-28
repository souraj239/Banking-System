package com.cognizant.transactionmicroservice.service;


import com.cognizant.transactionmicroservice.exceptions.InvalidTokenException;

import org.springframework.stereotype.Service;

@Service
public interface TokenValidationService {
    

	
	public boolean checkValidity(String Token) throws InvalidTokenException;
    
}
