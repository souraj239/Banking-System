package com.cognizant.transactionmicroservice.client;

import com.cognizant.transactionmicroservice.dto.CustomerDetails;
import com.cognizant.transactionmicroservice.dto.TransactionDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import io.swagger.annotations.Api;

/**
 * -> URL="http:localhost:8009/CustomerServices".
 * Use post mapping to perform transactions.
 * It returns the status of the customerDetails.
 * 
 * We use get mapping to get the transaction History.
 */
@FeignClient(name="customer-ms",url="http://localhost:8092/")
@Api(value="Interacting with customer microservice using feign client")
public interface CustomerClient {


    /**
	 * This method is to get the customer account details.
	 * @param token
	 * @return
	 */

    @GetMapping("/viewAccount")
    public ResponseEntity<CustomerDetails> viewCustomerAccount(@RequestHeader(name = "Authorization",required = true)String token);


    /**
	 * This method is to withdraw the amount from customer account.
	 * @param token
     * @param transactionDTO
	 * @return
	 */

    @PostMapping("/withdrawAmount")
    public ResponseEntity<Boolean> withdrawAmount(@RequestHeader(name = "Authorization",required = true)String token, @RequestBody TransactionDTO transaction);

        /**
	 * This method is to deposit the amount from customer account.
	 * @param token
     * @param transactionDTO
	 * @return
	 */
    @PostMapping("/depositAmount")
    public ResponseEntity<Boolean> depositAmount(@RequestHeader(name = "Authorization",required = true)String token, @RequestBody TransactionDTO transaction);
    
    
}
