package com.cognizant.transactionmicroservice.service;


import org.springframework.stereotype.Service;

@Service
public interface TokenValidationService {
    
		public boolean checkValidity(String Token);
    
}
