package com.homework.triple.exception;

public enum ErrorCode {

    // Common
    REQUIRED_FIELD_EMPTY(1000),

    // City

    // Travel
    START_DATE_IS_NOT_BEFORE_THE_END_DATE(3000),
    END_DATE_IS_NOT_FUTURE(3001);


    private final Integer errorCode;

    private ErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

}
