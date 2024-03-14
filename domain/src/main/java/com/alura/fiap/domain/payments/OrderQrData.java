package com.alura.fiap.domain.payments;

public record OrderQrData(String orderId,
                          String customerId,
                          String orderIdentifier,
                          String inStoreOrderId,
                          String qrData) {

    public static OrderQrData with(final String orderId,
                                   final String customerId,
                                   final String orderIdentifier,
                                   final String inStoreOrderId,
                                   final String qrData) {
        return new OrderQrData(orderId, customerId, orderIdentifier, inStoreOrderId, qrData);
    }
}
