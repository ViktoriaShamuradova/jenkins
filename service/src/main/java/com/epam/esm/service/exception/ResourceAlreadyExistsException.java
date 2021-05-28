package com.epam.esm.service.exception;


public class ResourceAlreadyExistsException extends ServiceException {

    public ResourceAlreadyExistsException(String errorCode, String message) {
        super(errorCode, message);
    }

    public ResourceAlreadyExistsException(String errorCode) {
        super(errorCode);
    }
}
