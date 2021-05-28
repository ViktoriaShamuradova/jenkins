package com.epam.esm.service.exception;

public enum ExceptionCode {

    NOT_SUPPORTED_OPERATION("50100"),

    TAG_ALREADY_EXIST("40902"),

    NO_SUCH_CERTIFICATE_FOUND("40401"),
    NO_SUCH_USER_FOUND("40403"),
    NO_SUCH_TAG_FOUND("40402"),
    NO_SUCH_ORDER_FOUND("40404"),

    NOT_VALID_PAGE_INFO("40005"),
    NOT_VALID_ID("40007"),


    ACCESS_IS_DENIED("40300"),
    UNAUTHORIZED("40301"),
    WRONG_PASSWORD_USERNAME ("40302"),

    USER_ALREADY_EXIST ("40903"),

    DEFAULT_ERROR_CODE("50100");

    private final String errorCode;

    ExceptionCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
