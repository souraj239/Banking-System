package com.cognizant.customermicroservice.exception;

public class MinimumBalaceException extends RuntimeException {
    /**
	 * Its a exception class.
	 * @param message
	 */
    
    public MinimumBalaceException(String message){
        super(message);
    }

    public MinimumBalaceException(){}
}
