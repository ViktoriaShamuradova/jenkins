package com.epam.esm.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchResourceException extends ServiceException {

    public NoSuchResourceException(String errorCode, String message) {
        super(errorCode, message);
    }

    public NoSuchResourceException(String errorCode) {
        super(errorCode);
    }
}
