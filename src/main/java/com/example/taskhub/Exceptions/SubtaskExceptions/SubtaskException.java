package com.example.taskhub.Exceptions.SubtaskExceptions;

import com.example.taskhub.Exceptions.ExceptionsDetails;

public class SubtaskException extends RuntimeException {
    private ExceptionsDetails details;

    public SubtaskException(String message, ExceptionsDetails details, Throwable e) {
        super(message, e);
        setDetails(details);
    }

    public SubtaskException(String message, ExceptionsDetails details) {
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
