package com.cognizant.customermicroservice.exception;

import java.time.LocalDateTime;

public class ExceptionResponse {
    /**
	 * Its a exception class.
	 * @param message
	 */
    private String message;
    private LocalDateTime dateTime;
    
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }    
    
}
