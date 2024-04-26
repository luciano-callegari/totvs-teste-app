package com.appTeste.domain.exception;

public class ContaNotFoundException extends BaseException {
    public ContaNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }
}
