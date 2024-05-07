package com.example.taskhub.Exceptions.ProjectExceptions;

import com.example.taskhub.Exceptions.ExceptionsDetails;

public class ProjectException extends RuntimeException {
    private ExceptionsDetails details;

    public ProjectException(String message, ExceptionsDetails details, Throwable e) {
        super(message);
        setDetails(details);
    }

    public ProjectException(String message, ExceptionsDetails details) {
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
