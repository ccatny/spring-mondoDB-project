package com.example.demo.exception;

public final class ServiceException extends RuntimeException {
    private Integer code;

    private String errorMessage;

    // inner use
    private String detailMessage;

    public ServiceException() {
    }

    public ServiceException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ServiceException(String errorMessage, Integer code) {
        this.errorMessage = errorMessage;
        this.code = code;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }

    public Integer getCode() {
        return code;
    }

    public ServiceException setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

}