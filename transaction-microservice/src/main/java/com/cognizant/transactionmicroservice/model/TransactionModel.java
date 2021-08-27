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
@Table(name="Transactions")
public class TransactionModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ApiModelProperty(value="todays date in which Transaction performed")
    @Temporal(TemporalType.DATE)
    private Date transactionDate=new Date();

    private String userName;

    private String from;

    private String to;

    public TransactionModel( String userName, String from, String to, String description, String transactiontype, long amount) {
        this.userName = userName;
        this.from = from;
        this.to = to;
        this.description = description;
        this.transactiontype = transactiontype;
        this.amount = amount;
    }

    private String description;

    private String transactiontype;

    private long amount;

}