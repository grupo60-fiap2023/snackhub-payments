package com.alura.fiap.application.create;

public record CreateOrderQrCodeCashOutCommand(Double amount) {

    public static CreateOrderQrCodeCashOutCommand with(final Double amount) {
        return new CreateOrderQrCodeCashOutCommand(amount);
    }
}
