package com.cognizant.customermicroservice.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String msg){
        super(msg);
    }

    public AccountNotFoundException(){}
}