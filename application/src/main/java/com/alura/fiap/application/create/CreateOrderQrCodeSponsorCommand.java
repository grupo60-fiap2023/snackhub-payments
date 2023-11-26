package com.alura.fiap.application.create;

public record CreateOrderQrCodeSponsorCommand(Integer id) {

    public static CreateOrderQrCodeSponsorCommand with(final Integer id) {
        return new CreateOrderQrCodeSponsorCommand(id);
    }
}
