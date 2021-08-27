package com.cognizant.transactionmicroservice.controller;

import com.cognizant.transactionmicroservice.service.TokenValidationService;
import com.cognizant.transactionmicroservice.client.CustomerClient;
import com.cognizant.transactionmicroservice.dto.CustomerDetails;
import com.cognizant.transactionmicroservice.dto.StatementDTO;
import com.cognizant.transactionmicroservice.dto.TransactionDTO;
import com.cognizant.transactionmicroservice.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin("*")
public class TransactionController {

    @Autowired
    private TokenValidationService tokenValidator;

    @Autowired
    private TransactionRecordService transactionRecordService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CustomerClient client;
    
    @PostMapping("/transferMoney")
    public ResponseEntity<?> transferMoney(@RequestHeader(name = "Authorization",required = true)String token,@RequestBody TransactionDTO toUser){
        if(tokenValidator.checkValidity(token)){
            log.info("token validated");
            if(transactionService.transferAmount(token, client.viewCustomerAccount(token).getBody(), toUser)){
                return new ResponseEntity<CustomerDetails>(client.viewCustomerAccount(token).getBody(), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<String>("Account Not Found",HttpStatus.BAD_REQUEST);
            }
        }else{
            log.error("token expired");
			return new ResponseEntity<String>("Token Invalid or expired",HttpStatus.FORBIDDEN);
        }
    } 

    @PostMapping("/cashWithdraw")
    public ResponseEntity<?> cashWithdraw(@RequestHeader(name = "Authorization",required = true)String token,@RequestBody long amount){
        if(tokenValidator.checkValidity(token)){
            log.info("token validated");
            TransactionDTO transactionDTO=new TransactionDTO(client.viewCustomerAccount(token).getBody().getUserName(),amount);
            if(transactionService.cashWithdraw(token, transactionDTO)){
                return new ResponseEntity<CustomerDetails>(client.viewCustomerAccount(token).getBody(), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<String>("Minimum Balance Error",HttpStatus.BAD_REQUEST);
            }
        }else{
            log.error("token expired");
			return new ResponseEntity<String>("Token Invalid or expired",HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/cashDeposit")
    public ResponseEntity<?> cashDeposit(@RequestHeader(name = "Authorization",required = true)String token,@RequestBody long amount){
        if(tokenValidator.checkValidity(token)){
            log.info("token validated");
            TransactionDTO transactionDTO=new TransactionDTO(client.viewCustomerAccount(token).getBody().getUserName(),amount);
            transactionService.depositCash(token, transactionDTO);
            return new ResponseEntity<CustomerDetails>(client.viewCustomerAccount(token).getBody(), HttpStatus.OK);
        }
        else{
            log.error("token expired");
			return new ResponseEntity<String>("Token Invalid or expired",HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/getStatement")
    @ApiOperation(value = "Statement info", notes = "gives user Bank Statement", httpMethod = "GET", response = ResponseEntity.class)
    public ResponseEntity<?> auditHistory(@RequestHeader(name = "Authorization",required = true)String token){
        if(tokenValidator.checkValidity(token)){
            CustomerDetails customer= client.viewCustomerAccount(token).getBody();
            List<StatementDTO> customerStatement=transactionRecordService.getStatement(customer);
            return new ResponseEntity<List<StatementDTO>>(customerStatement,HttpStatus.OK);
        }
        else{
            log.error("token expired");
			return new ResponseEntity<String>("Token Invalid or expired",HttpStatus.FORBIDDEN);
        }
    }




}