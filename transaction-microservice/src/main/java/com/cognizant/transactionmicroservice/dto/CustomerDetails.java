package com.cognizant.transactionmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetails {

    private String userName;

    private String customerName;

    private String accountNumber;

    private long accountBalance;

    
}
