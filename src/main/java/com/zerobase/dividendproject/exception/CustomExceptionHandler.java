package com.zerobase.dividendproject.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(AbstractExeption.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(AbstractExeption ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(ex.getStatusCode())
                .message(ex.getMessage()).build();
        log.warn(String.format("[%d] -> [%s]", ex.getStatusCode(), ex.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.resolve(ex.getStatusCode()));
    }
}
