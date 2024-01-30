package com.alura.fiap.application.create;

import org.junit.experimental.categories.Categories;

@Categories.ExcludeCategory
public record CreateOrderQrCodeItemCommand(

        String title,
        String unitMeasure,
        Double unitPrice,
        Integer quantity,
        Double totalAmount) {

    public static CreateOrderQrCodeItemCommand with(
            final String title,
            final String unitMeasure,
            final Double unitPrice,
            final Integer quantity,
            final Double totalAmount
    ) {
        return new CreateOrderQrCodeItemCommand(
                title,
                unitMeasure,
                unitPrice,
                quantity,
                totalAmount
        );
    }
}
