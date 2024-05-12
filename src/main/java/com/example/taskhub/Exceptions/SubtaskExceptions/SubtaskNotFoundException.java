package com.example.taskhub.Exceptions.SubtaskExceptions;

import com.example.taskhub.Exceptions.ExceptionsDetails;

public class SubtaskNotFoundException extends Exception {
    private ExceptionsDetails details;

    public SubtaskNotFoundException(String message, ExceptionsDetails details, Throwable e) {
        super(message, e);
        setDetails(details);
    }

    public SubtaskNotFoundException(String message, ExceptionsDetails details) {
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
