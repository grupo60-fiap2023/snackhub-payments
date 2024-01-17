package com.alura.fiap.domain.payments;

import com.mercadopago.resources.payment.Payment;

public interface PaymentGateway {

    Payment createPayment(String authorization, CreatePayment request);
}
