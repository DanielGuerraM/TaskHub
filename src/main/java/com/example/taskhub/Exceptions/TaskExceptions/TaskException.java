package com.example.taskhub.Exceptions.TaskExceptions;

import com.example.taskhub.Exceptions.ExceptionsDetails;

public class TaskException extends Exception{
    private ExceptionsDetails details;

    public TaskException(String message, ExceptionsDetails details, Throwable e) {
        super(message, e);
        setDetails(details);
    }

    public TaskException(String message, ExceptionsDetails details) {
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
