package com.example.taskhub.Exceptions.UserExceptions;

import com.example.taskhub.Exceptions.ExceptionsDetails;

public class UserNotFoundException extends RuntimeException{
    private ExceptionsDetails details;

    public UserNotFoundException(String message, ExceptionsDetails details, Throwable e) {
        super(message, e);
        setDetails(details);
    }

    public UserNotFoundException(String message, ExceptionsDetails details) {
        super(message);
        setDetails(details);
    }

    public ExceptionsDetails getDetails() {
        return details;
    }

    public void setDetails(ExceptionsDetails details) {
        this.details = details;
    }
}
