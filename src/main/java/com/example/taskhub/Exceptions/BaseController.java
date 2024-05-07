package com.example.taskhub.Exceptions;

import com.example.taskhub.Exceptions.ContactExceptions.ContactException;
import com.example.taskhub.Exceptions.ContactExceptions.ContactNotFoundException;
import com.example.taskhub.Exceptions.ProjectExceptions.ProjectException;
import com.example.taskhub.Exceptions.ProjectExceptions.ProjectNotFoundException;
import com.example.taskhub.Exceptions.SubtaskExceptions.SubtaskException;
import com.example.taskhub.Exceptions.SubtaskExceptions.SubtaskNotFoundException;
import com.example.taskhub.Exceptions.TaskExceptions.TaskException;
import com.example.taskhub.Exceptions.TaskExceptions.TaskNotFoundException;
import com.example.taskhub.Exceptions.TrackingExceptions.TrackingException;
import com.example.taskhub.Exceptions.TrackingExceptions.TrackingNotFoundException;
import com.example.taskhub.Exceptions.UserExceptions.EmailExistingException;
import com.example.taskhub.Exceptions.UserExceptions.UserException;
import com.example.taskhub.Exceptions.UserExceptions.UserNameExistingException;
import com.example.taskhub.Exceptions.UserExceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class.getName());

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ExceptionsDetails> handleUserExceptions(UserException exception) {
        LOG.error(exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getDetails(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionsDetails> handleUserNotFoundExceptions(UserNotFoundException exception) {
        LOG.error(exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getDetails(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNameExistingException.class)
    public ResponseEntity<ExceptionsDetails> handleUserNameExistingExceptions(UserNameExistingException exception) {
        LOG.error(exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getDetails(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailExistingException.class)
    public ResponseEntity<ExceptionsDetails> handleEmailExistingException(EmailExistingException exception) {
        LOG.error(exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getDetails(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TaskException.class)
    public ResponseEntity<ExceptionsDetails> handleTaskException(TaskException exception) {
        LOG.error(exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getDetails(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ExceptionsDetails> handleTaskNotFoundException(TaskNotFoundException exception) {
        LOG.error(exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getDetails(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProjectException.class)
    public ResponseEntity<ExceptionsDetails> handleProjectException(ProjectException exception) {
        LOG.error(exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getDetails(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<ExceptionsDetails> handleProjectNotFoundException(ProjectNotFoundException exception) {
        LOG.error(exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getDetails(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SubtaskException.class)
    public ResponseEntity<ExceptionsDetails> handleSubtaskException(SubtaskException exception) {
        LOG.error(exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getDetails(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SubtaskNotFoundException.class)
    public ResponseEntity<ExceptionsDetails> handleSubtaskNotFoundException(SubtaskNotFoundException exception) {
        LOG.error(exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getDetails(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TrackingNotFoundException.class)
    public ResponseEntity<ExceptionsDetails> handleTrackingNotFoundException(TrackingNotFoundException exception) {
        LOG.error(exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getDetails(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TrackingException.class)
    public ResponseEntity<ExceptionsDetails> handleTrackingException(TrackingException exception) {
        LOG.error(exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getDetails(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity<ExceptionsDetails> handleContactNotFoundException(ContactNotFoundException exception) {
        LOG.error(exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getDetails(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ContactException.class)
    public ResponseEntity<ExceptionsDetails> handleContactException(ContactException exception) {
        LOG.error(exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getDetails(), HttpStatus.BAD_REQUEST);
    }
}