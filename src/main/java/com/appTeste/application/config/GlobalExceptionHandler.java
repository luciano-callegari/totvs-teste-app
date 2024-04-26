package com.appTeste.application.config;


import com.appTeste.domain.exception.ValidationException;
import com.appTeste.domain.model.ErrorReturn;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ValidationException.class})
    public ErrorReturn handleException(ValidationException e, HttpServletRequest request) {
        return new ErrorReturn(e.getMessage(), e.getErrorCode());
    }
}
