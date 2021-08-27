package com.cognizant.customermicroservice.model;

import javax.persistence.Column;
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
@Table(name="customer")
public class CustomerDetails {

    @Id
    @Column(name="username")
    private String userName;

    private String customerName;

    private String accountNumber;

    private long accountBalance;

    
}
