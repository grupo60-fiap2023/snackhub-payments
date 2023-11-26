package com.alura.fiap.application.create;

public record CreateImageQrCodeCommand(String data) {

    public static CreateImageQrCodeCommand with(final String data) {
        return new CreateImageQrCodeCommand(data);
    }
}
