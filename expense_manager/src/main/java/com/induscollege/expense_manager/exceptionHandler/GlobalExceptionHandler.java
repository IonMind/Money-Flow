package com.induscollege.expense_manager.exceptionHandler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

// @ControllerAdvice
public class GlobalExceptionHandler {

    @Value("${spring.application.name}")
    private String appName;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleCustomException(Exception e) {

        return new ResponseEntity<String>(appName+":Error => " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
