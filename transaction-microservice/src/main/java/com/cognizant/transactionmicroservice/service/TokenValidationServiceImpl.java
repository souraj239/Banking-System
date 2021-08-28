package com.cognizant.transactionmicroservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.transactionmicroservice.auth.AuthClient;
import com.cognizant.transactionmicroservice.exceptions.InvalidTokenException;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TokenValidationServiceImpl implements TokenValidationService {

	@Autowired
	private AuthClient authClient;

        
    /**
     * This method will validate the token whether the token is valid or expired. 
     * @param token 
     * @return status of token validity
     */
	    @Override
	    public boolean checkValidity(String token) {
	        boolean valid = false;
			log.info(token);
			try {
				if (!authClient.getsValidity(token).isValidStatus()) {
					valid=false;
					log.info("Invalid Token");
					throw new InvalidTokenException("Token is either expired or invalid...");
				}
			}
			catch(FeignException e) {
				valid =false;
				throw new InvalidTokenException("Token is either expired or invalid...");
			}
			valid=true;
			return valid;
			}    
}
