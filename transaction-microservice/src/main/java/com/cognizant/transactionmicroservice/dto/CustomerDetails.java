package com.cognizant.transactionmicroservice.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="customers")
public class CustomerDetails {

    @Id
    private String userName;

    private String customerName;

    private String accountNumber;

    private long accountBalance;

    
}
