package com.example.taskhub.Exceptions.ProjectExceptions;

import com.example.taskhub.Exceptions.ExceptionsDetails;

public class ProjectNotFoundException extends RuntimeException{
    private ExceptionsDetails details;

    public ProjectNotFoundException(String message, ExceptionsDetails details, Throwable e) {
        super(message);
        setDetails(details);
    }

    public ProjectNotFoundException(String message, ExceptionsDetails details) {
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
