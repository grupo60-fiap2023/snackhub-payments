package com.alura.fiap.infrastructure.models;

public class OrderQrCodeResponseWrapper {

    private final byte[] mediaType;

    public OrderQrCodeResponseWrapper(byte[] mediaType) {
        this.mediaType = mediaType;
    }

    public static OrderQrCodeResponseWrapper with(byte[] mediaType) {
        return new OrderQrCodeResponseWrapper(mediaType);
    }

    public byte[] getMediaType() {
        return mediaType;
    }
}
