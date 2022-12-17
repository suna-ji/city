package com.homework.triple.exception;

public enum ErrorCode {

    // Common
    REQUIRED_FIELD_EMPTY(1000);
    // City


    // Travle

    private Integer errorCode;

    ErrorCode(int i) {
    }

    public String getErrorCode() {
        return String.valueOf(errorCode);
    }

}
