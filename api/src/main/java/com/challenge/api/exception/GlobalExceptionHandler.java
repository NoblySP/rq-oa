package com.challenge.api.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Uses @ControllerAdvice to intercept exceptions
 * thrown from any @RestController.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @param ex The exception thrown when an employee is not found.
     * @return A ResponseEntity with a 404 status and error message.
     */
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> handleEmployeeNotFound(EmployeeNotFoundException ex) {

        Map<String, Object> body = new HashMap<>();
        body.put("error_message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * @param ex The exception thrown when an employee already exists.
     * @return A ResponseEntity with a 409 status and error message.
     */
    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    public ResponseEntity<Object> handleEmployeeAlreadyExists(EmployeeAlreadyExistsException ex) {

        Map<String, Object> body = new HashMap<>();
        body.put("error_message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
}
