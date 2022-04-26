package com.zivu.hiring.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Handles exceptions that arise during REST calls enriching them with useful information.
 */
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class, MissingServletRequestParameterException.class})
    public ResponseEntity<String> handleGenericExceptions(Exception exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}
