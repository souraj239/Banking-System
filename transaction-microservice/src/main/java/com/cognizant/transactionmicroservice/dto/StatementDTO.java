package com.cognizant.transactionmicroservice.dto;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "model class for statement fields")
public class StatementDTO {

    private Date transactionDate;

    private String description;

    private String transactionType;

    private long Amount;

    
}
