package com.epam.esm.service.exception;


public class ServiceException extends RuntimeException {

    private final String errorCode;

    ServiceException(String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    ServiceException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
