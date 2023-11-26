package com.alura.fiap.domain.payments;

public interface FileQrCodeGateway {

    byte[] createImageQRCode(String data);
}
