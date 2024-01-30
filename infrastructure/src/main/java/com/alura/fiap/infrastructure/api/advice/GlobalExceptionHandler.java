package com.alura.fiap.infrastructure.api.advice;

import com.alura.fiap.application.execeptions.MerchantOrderNotFoundException;
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
        return new ResponseEntity<>("Ocorreu um erro interno, tente novamente mais tarde.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MerchantOrderNotFoundException.class)
    public ResponseEntity<String> handlerMerchantNotFound(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

    }
}
