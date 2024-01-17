package com.alura.fiap.application;


import com.mercadopago.resources.payment.Payment;

public record PaymentOutput(Payment payment) {

    public static PaymentOutput from(Payment payment) {
        return new PaymentOutput(payment);
    }
}