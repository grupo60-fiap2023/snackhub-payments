package com.alura.fiap.application;

public abstract class AuthPaymentMPUseCase<IN, OUT> {

    public abstract OUT execute(String authorization, IN inputObject);
}
