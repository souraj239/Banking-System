package com.cognizant.transactionmicroservice.controller;

import com.cognizant.transactionmicroservice.service.TokenValidationService;
import com.cognizant.transactionmicroservice.client.CustomerClient;
import com.cognizant.transactionmicroservice.dto.AmountDTO;
import com.cognizant.transactionmicroservice.dto.CustomerDetails;
import com.cognizant.transactionmicroservice.dto.StatementDTO;
import com.cognizant.transactionmicroservice.dto.TransactionDTO;
import com.cognizant.transactionmicroservice.dto.TempTransactionDTO;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

/**
 * -> URL="http:localhost:8009/CustomerServices".
 * Use post mapping to perform transactions.
 * It returns the status of the customerDetails.
 * 
 * We use get mapping to get the transaction History.
 */

@RestController
@Api(value="End point for transaction microservice to perform banking execution process")
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

      /**
     * This method gives the transfer money from source to reciepient.
     * @param token.
     * @param toUser.
     * @return response.
     */
    @PostMapping("/transferMoney")
    @ApiOperation(value = "Money Transfer", notes = "takes user receptient details like user Name, amount to transfer the amount", httpMethod = "POST", response = ResponseEntity.class)
    public ResponseEntity<?> transferMoney(@ApiParam (name = "Money Transfer", value = "details of the Recieptent")@RequestHeader(name = "Authorization",required = true)String token,@RequestBody TempTransactionDTO tempToUser){
        log.info(tempToUser.getAmount());
        TransactionDTO toUser=tempToUser.getConverted();
        if(tokenValidator.checkValidity(token)){
            log.info("token validated");
            if(transactionService.transferAmount(token, client.viewCustomerAccount(token).getBody(), toUser)){
                return new ResponseEntity<CustomerDetails>(client.viewCustomerAccount(token).getBody(), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<CustomerDetails>(client.viewCustomerAccount(token).getBody(),HttpStatus.BAD_REQUEST);
            }
        }else{
            log.error("token expired");
			return new ResponseEntity<String>("Token Invalid or expired",HttpStatus.FORBIDDEN);
        }
    } 

     /**
     * This method withdraw money from account.
     * @param token.
     * @param amount.
     * @return response.
     */
    @PostMapping("/cashWithdraw")
    @ApiOperation(value = "cash withdraw", notes = "Withdraw Cash from the user account", httpMethod = "POST", response = ResponseEntity.class)
    public ResponseEntity<?> cashWithdraw(@ApiParam (name = "Chash Withdraw", value = "amount")@RequestHeader(name = "Authorization",required = true)String token,@RequestBody AmountDTO amount){
        if(tokenValidator.checkValidity(token)){
            log.info("token validated");
            TransactionDTO transactionDTO=new TransactionDTO(client.viewCustomerAccount(token).getBody().getUserName(),amount.getAmount());
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

    
     /**
     * This method deposit money to the account.
     * @param token.
     * @param amount.
     * @return response.
     */
    @PostMapping("/cashDeposit")
    @ApiOperation(value = "cash Deposit", notes = "Deposit Cash to the user account", httpMethod = "POST", response = ResponseEntity.class)
    public ResponseEntity<?> cashDeposit(@ApiParam (name = "Deposit Cash", value = "amount")@RequestHeader(name = "Authorization",required = true)String token,@RequestBody AmountDTO amount){
        if(tokenValidator.checkValidity(token)){
            log.info("token validated");
            TransactionDTO transactionDTO=new TransactionDTO(client.viewCustomerAccount(token).getBody().getUserName(),amount.getAmount());
            transactionService.depositCash(token, transactionDTO);
            return new ResponseEntity<CustomerDetails>(client.viewCustomerAccount(token).getBody(), HttpStatus.OK);
        }
        else{
            log.error("token expired");
			return new ResponseEntity<String>("Token Invalid or expired",HttpStatus.FORBIDDEN);
        }
    }

    /**
     * This method gets the statement the account.
     * @param token
     * @return response
     */
    @GetMapping("/getStatement")
    @ApiOperation(value = "Statement info", notes = "gives user Bank Statement", httpMethod = "GET", response = ResponseEntity.class)
    public ResponseEntity<?> getSTatement(@ApiParam (name = "transaction info", value = "get account statemnt of the user")@RequestHeader(name = "Authorization",required = true)String token){
        log.info("msg");
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