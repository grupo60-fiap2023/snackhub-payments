 package com.alura.fiap.application;


 import com.alura.fiap.domain.payments.OrderQrCodeOut;

 public record OrderQrCodeOutput(String inStoreOrderId, String qrData) {

    public static OrderQrCodeOutput from(OrderQrCodeOut orderQrCodeOut) {
        return new OrderQrCodeOutput(orderQrCodeOut.inStoreOrderId(), orderQrCodeOut.qrData());
    }
}
