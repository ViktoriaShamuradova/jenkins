package com.epam.esm.service.exception;

public class ValidationException extends ServiceException {

    public ValidationException(String errorCode, String message) {
        super(errorCode, message);
    }

    public ValidationException(String errorCode) {
        super(errorCode);
    }
}
