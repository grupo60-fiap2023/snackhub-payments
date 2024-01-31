package com.alura.fiap.application.execeptions;

public class MerchantOrderNotFoundException extends RuntimeException {

    public MerchantOrderNotFoundException(String message) {
        super(message);
    }
}

