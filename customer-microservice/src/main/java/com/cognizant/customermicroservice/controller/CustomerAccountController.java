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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * -> URL="http:localhost:8089/".
 * Use post mapping to perform transactions.
 * It returns the status of the customerDetails.
 * We use get mapping to get the customer details.
 */

@RestController
@CrossOrigin("*")
@Slf4j
@Api(value="End point for transaction microservice to perform banking execution process")
public class CustomerAccountController {

    @Autowired
	private TokenValidationService tokenValidator;

    @Autowired
    private CustomerAccountService accountService;


      /**
     * This method gets the statement the account.
     * @param token
     * @return response
     */
    @GetMapping("/viewAccount")
    @ApiOperation(value = "Customer Accounr info", notes = "gives user Account details", httpMethod = "GET", response = ResponseEntity.class)
    public ResponseEntity<?> viewCustomerAccount(@ApiParam (name = "account info", value = "get account details of the user")@RequestHeader(name = "Authorization",required = true)String token){
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

      /**
     * This method withdraw money from account.
     * @param token.
     * @param transaction
     * @return response.
     */

    @PostMapping("/withdrawAmount")
    @ApiOperation(value = "cash withdraw", notes = "Withdraw Cash from the given account", httpMethod = "POST", response = ResponseEntity.class)
    public ResponseEntity<?> withdrawAmount(@ApiParam (name = "Chash Withdraw", value = "amount")@RequestHeader(name = "Authorization",required = true)String token, @RequestBody TransactionDTO transaction){
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

    
     /**
     * This method deposit money to the account.
     * @param token.
     * @param transaction
     * @return response.
     */
    @PostMapping("/depositAmount")
    @ApiOperation(value = "cash Deposit", notes = "Deposit Cash to the user account", httpMethod = "POST", response = ResponseEntity.class)
    public ResponseEntity<?> depositAmount(@ApiParam (name = "Deposit Cash", value = "amount")@RequestHeader(name = "Authorization",required = true)String token, @RequestBody TransactionDTO transaction){
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
