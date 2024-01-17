package com.alura.fiap.application;

public abstract class AuthCardTokenMPUseCase<IN, OUT> {

    public abstract OUT execute(String authorization, IN inputObject, String publicKey);
}
