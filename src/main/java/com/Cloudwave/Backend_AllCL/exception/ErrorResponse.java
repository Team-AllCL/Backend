package com.Cloudwave.Backend_AllCL.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private int status;
    private String errorCode;
    private String message;

    public ErrorResponse(int status, String errorCode, String message) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
    }
}
