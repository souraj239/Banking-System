package com.cognizant.transactionmicroservice.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "model class for transaction details")
public class TransactionDTO {

    private String userName;

    private long amount;
    
}
