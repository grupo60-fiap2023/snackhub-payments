package com.alura.fiap.domain.payments;

public record OrderStatusProducer(String orderId,
                                  String status) {

    public static OrderStatusProducer with(final String orderId,
                                           final String status) {
        return new OrderStatusProducer(orderId, status);
    }
}
