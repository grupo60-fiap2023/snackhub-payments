package com.alura.fiap.domain.payments;

public record OrderQrCodeOut(String inStoreOrderId, String qrData) {

    public static OrderQrCodeOut createOrderQrCodeOut(String inStoreOrderId, String qrData) {
        return new OrderQrCodeOut(inStoreOrderId, qrData);
    }
}
