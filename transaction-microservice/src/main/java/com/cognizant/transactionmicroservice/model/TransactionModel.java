package com.cognizant.transactionmicroservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="transactions")
public class TransactionModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ApiModelProperty(value="todays date in which Transaction performed")
    @Temporal(TemporalType.DATE)
    private Date transactionDate=new Date();

    private String userName;

    private String fromAc;

    private String toAc;
    
    private String description;
    
    private String transactiontype;
    
    private long amount;
    
    public TransactionModel( String userName, String fromAc, String toAc, String description, String transactiontype, long amount) {
        this.userName = userName;
        this.fromAc = fromAc;
        this.toAc = toAc;
        this.description = description;
        this.transactiontype = transactiontype;
        this.amount = amount;
    }

}
