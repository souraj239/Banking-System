package com.cognizant.customermicroservice.service;

import org.springframework.stereotype.Service;

@Service
public interface TokenValidationService {
    
		public boolean checkValidity(String Token);
		public String getcurrentUser(String token);
    
}
