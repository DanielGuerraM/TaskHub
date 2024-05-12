package com.example.taskhub.Exceptions.ContactExceptions;

import com.example.taskhub.Exceptions.ExceptionsDetails;

public class ContactException extends Exception {
    private ExceptionsDetails details;

    public ContactException(String message, ExceptionsDetails details, Throwable e) {
        super(message);
        setDetails(details);
    }

    public ContactException(String message, ExceptionsDetails details) {
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
