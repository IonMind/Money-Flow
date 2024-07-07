package com.induscollege.api_gateway.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleCustomException(Exception e) {

        return new ResponseEntity<String>("ApiGW:Error => " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
