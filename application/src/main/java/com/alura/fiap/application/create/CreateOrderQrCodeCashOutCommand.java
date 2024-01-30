package com.alura.fiap.application.create;

import org.junit.experimental.categories.Categories;

@Categories.ExcludeCategory
public record CreateOrderQrCodeCashOutCommand(Double amount) {

    public static CreateOrderQrCodeCashOutCommand with(final Double amount) {
        return new CreateOrderQrCodeCashOutCommand(amount);
    }
}
