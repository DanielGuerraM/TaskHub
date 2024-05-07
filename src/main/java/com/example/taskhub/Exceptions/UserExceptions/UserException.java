package com.example.taskhub.Exceptions.UserExceptions;

import com.example.taskhub.Exceptions.ExceptionsDetails;
import lombok.Data;

public class UserException extends RuntimeException{
    private ExceptionsDetails details;

    public UserException(String message, ExceptionsDetails details, Throwable e) {
        super(message, e);
        setDetails(details);
    }

    public UserException(String message, ExceptionsDetails details) {
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
