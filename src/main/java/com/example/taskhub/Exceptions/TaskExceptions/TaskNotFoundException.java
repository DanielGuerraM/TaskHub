package com.example.taskhub.Exceptions.TaskExceptions;

import com.example.taskhub.Exceptions.ExceptionsDetails;

public class TaskNotFoundException extends RuntimeException {
    private ExceptionsDetails details;

    public TaskNotFoundException(String message, ExceptionsDetails details, Throwable e) {
        super(message);
        setDetails(details);

    }

    public TaskNotFoundException(String message, ExceptionsDetails details) {
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
