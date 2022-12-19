package com.homework.triple.exception;

public enum ErrorCode {

    // Common
    REQUIRED_FIELD_EMPTY(1000);

    // City

    // Travle

    private final Integer errorCode;

    private ErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

}
