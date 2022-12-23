package com.homework.triple.exception;

public enum ErrorCode {

    // Common
    REQUIRED_FIELD_EMPTY(1000),

    // City
    UNABLE_TO_DELETE_CITY_WITH_THE_TRAVEL(2000),


    // Travel
    START_DATE_IS_NOT_BEFORE_THE_END_DATE(3000),
    END_DATE_IS_NOT_FUTURE(3001),
    CITY_LIST_IS_EMPTY(3002);


    private final Integer errorCode;

    private ErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

}
