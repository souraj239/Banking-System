package com.cognizant.customermicroservice.controller;

import com.cognizant.customermicroservice.service.CustomerAccountService;
import com.cognizant.customermicroservice.service.TokenValidationService;
import com.cognizant.customermicroservice.dto.TransactionDTO;
import com.cognizant.customermicroservice.model.CustomerDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@Slf4j
public class CustomerAccountController {

    @Autowired
	private TokenValidationService tokenValidator;

    @Autowired
    private CustomerAccountService accountService;


    @GetMapping("/viewAccount")
    public ResponseEntity<?> viewCustomerAccount(@RequestHeader(name = "Authorization",required = true)String token){
        log.info("view method");
		if(tokenValidator.checkValidity(token)) {
			log.info("token validated");
            ResponseEntity<?> responseEntity;
            String user=tokenValidator.getcurrentUser(token);
            try{
                CustomerDetails customer =accountService.viewAccount(user);
                responseEntity= new ResponseEntity<CustomerDetails>(customer,HttpStatus.OK);
                return responseEntity;
            }catch(Exception e){
                responseEntity= new ResponseEntity<String>("account not found",HttpStatus.BAD_REQUEST);
				return responseEntity;
            }
        }  
        else {
			log.error("token expired");
			return new ResponseEntity<String>("Token Invalid or expired",HttpStatus.FORBIDDEN);
		}        
    }


    @PostMapping("/withdrawAmount")
    public ResponseEntity<?> withdrawAmount(@RequestHeader(name = "Authorization",required = true)String token, @RequestBody TransactionDTO transaction){
        log.info("withdrawt method");
		if(tokenValidator.checkValidity(token)) {
			log.info("token validated");
            ResponseEntity<?> responseEntity;
            try{
                boolean success=accountService.withdrawAmount(transaction);
                responseEntity= new ResponseEntity<Boolean>(success,HttpStatus.OK);
                return responseEntity;
            }catch(Exception e){
                responseEntity= new ResponseEntity<String>("Account Not Found",HttpStatus.BAD_REQUEST);
				return responseEntity;
            }
        }  
        else {
			log.error("token expired");
			return new ResponseEntity<String>("Token Invalid or expired",HttpStatus.FORBIDDEN);
		}
    }

    @PostMapping("/depositAmount")
    public ResponseEntity<?> depositAmount(@RequestHeader(name = "Authorization",required = true)String token, @RequestBody TransactionDTO transaction){
        log.info("withdraw method");
		if(tokenValidator.checkValidity(token)) {
			log.info("token validated");
            ResponseEntity<?> responseEntity;
            try{
                boolean result =accountService.depositAmount(transaction);
                responseEntity= new ResponseEntity<Boolean>(result,HttpStatus.OK);
                return responseEntity;
            }catch(Exception e){
                responseEntity= new ResponseEntity<String>("Account Not Found",HttpStatus.BAD_REQUEST);
				return responseEntity;
            }
        }  
        else {
			log.error("token expired");
			return new ResponseEntity<String>("Token Invalid or expired",HttpStatus.FORBIDDEN);
		}
    }
    
}
