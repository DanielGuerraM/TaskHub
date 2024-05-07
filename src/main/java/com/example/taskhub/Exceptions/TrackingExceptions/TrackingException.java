package com.example.taskhub.Exceptions.TrackingExceptions;

import com.example.taskhub.Exceptions.ExceptionsDetails;

public class TrackingException extends RuntimeException {
    private ExceptionsDetails details;

    public TrackingException(String message, ExceptionsDetails details, Throwable e) {
        super(message, e);
        setDetails(details);
    }

    public TrackingException(String message, ExceptionsDetails details) {
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
