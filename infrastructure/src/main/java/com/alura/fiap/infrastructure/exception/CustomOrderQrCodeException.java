package com.alura.fiap.infrastructure.exception;

public class CustomOrderQrCodeException extends RuntimeException {

    public CustomOrderQrCodeException(String message) {
        super(message);
    }
}
