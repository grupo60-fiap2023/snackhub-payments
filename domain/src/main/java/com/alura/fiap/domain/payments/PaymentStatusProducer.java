package com.alura.fiap.domain.payments;

public record PaymentStatusProducer(String orderId,
                                    String customerId,
                                    String status,
                                    String orderIdentifier) {

    public static PaymentStatusProducer with(final String orderId,
                                             final String customerId,
                                             final String status,
                                             final String orderIdentifier) {
        return new PaymentStatusProducer(orderId, customerId, status, orderIdentifier);
    }
}
