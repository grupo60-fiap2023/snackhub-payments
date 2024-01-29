package com.alura.fiap.infrastructure.api.advice;

import com.alura.fiap.infrastructure.exception.CustomOrderQrCodeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomOrderQrCodeException.class)
    public ResponseEntity<String> handleCustomException(CustomOrderQrCodeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("Ocorreu um erro interno.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
