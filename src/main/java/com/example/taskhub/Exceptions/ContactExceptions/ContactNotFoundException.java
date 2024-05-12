package com.example.taskhub.Exceptions.ContactExceptions;

import com.example.taskhub.Exceptions.ExceptionsDetails;

public class ContactNotFoundException extends Exception {
    private ExceptionsDetails details;

    public ContactNotFoundException(String message, ExceptionsDetails details, Throwable e) {
        super(message);
        setDetails(details);
    }

    public ContactNotFoundException(String message, ExceptionsDetails details) {
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
