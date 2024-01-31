package com.alura.fiap.domain.payments;

public interface OrderQrCodeGateway {

    OrderQrCodeOut createOrderQRCode(String authorization, OrderQrCode request, String userId, String externalPosId);
}
