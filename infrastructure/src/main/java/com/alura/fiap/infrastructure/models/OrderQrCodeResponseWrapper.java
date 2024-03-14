package com.alura.fiap.infrastructure.models;

public class OrderQrCodeResponseWrapper {

    public OrderQrCodeResponseWrapper(OrderQrCodeResponse orderQrCodeResponse) {
    }

    public static OrderQrCodeResponseWrapper with(OrderQrCodeResponse orderQrCodeResponse) {
        return new OrderQrCodeResponseWrapper(orderQrCodeResponse);
    }

}
