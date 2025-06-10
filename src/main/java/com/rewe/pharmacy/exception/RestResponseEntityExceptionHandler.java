package com.rewe.pharmacy.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleAuthenticationException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                "Authentication Failed!", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleAccessDeniedException(
            Exception exception, WebRequest request) {
        return new ResponseEntity<>(
                "Access denied for this user!", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Object> handleObjectNotFoundException(RuntimeException exception, WebRequest request) {
        String responseBody = "Object Not Found!";
        return handleExceptionInternal(exception, responseBody,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


}