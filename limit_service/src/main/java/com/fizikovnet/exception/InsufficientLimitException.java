package com.fizikovnet.exception;

public class InsufficientLimitException extends RuntimeException {
    public InsufficientLimitException(String message) {
        super(message);
    }
}
