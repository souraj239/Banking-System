package com.cognizant.transactionmicroservice.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatementDTO {

    private Date transactionDate;

    private String description;

    private String transactionType;

    private long Amount;

    
}
