package com.cognizant.customermicroservice.exception;

public class AccountNotFoundException extends RuntimeException {
    /**
	 * Its a exception class.
	 * @param message
	 */

    public AccountNotFoundException(String msg){
        super(msg);
    }

    public AccountNotFoundException(){}
}