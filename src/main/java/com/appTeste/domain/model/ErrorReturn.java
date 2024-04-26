package com.appTeste.domain.model;

import lombok.Data;

@Data
public class ErrorReturn {

    private String message;

    private String errorCode;

    public ErrorReturn(String message) {
        this.message = message;
    }

    public ErrorReturn(String message, String errorCode) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public ErrorReturn() {
    }
}
