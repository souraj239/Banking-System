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

@FeignClient(name="customer-ms",url="http://localhost:8092/")
@Api(value="Interacting with customer microservice using feign client")
public interface CustomerClient {

    @GetMapping("/viewAccount")
    public ResponseEntity<CustomerDetails> viewCustomerAccount(@RequestHeader(name = "Authorization",required = true)String token);

    @PostMapping("/withdrawAmount")
    public ResponseEntity<Boolean> withdrawAmount(@RequestHeader(name = "Authorization",required = true)String token, @RequestBody TransactionDTO transaction);

    @PostMapping("/depositAmount")
    public ResponseEntity<Boolean> depositAmount(@RequestHeader(name = "Authorization",required = true)String token, @RequestBody TransactionDTO transaction);
    
    
}
