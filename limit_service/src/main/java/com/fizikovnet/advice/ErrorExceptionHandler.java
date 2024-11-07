package com.fizikovnet.advice;

import com.fizikovnet.exception.InsufficientLimitException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorExceptionHandler {

    @ExceptionHandler(InsufficientLimitException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInsufficientLimitException(InsufficientLimitException ex) {
        return ex.getMessage();
    }

}
