package com.alura.fiap.application.create;

public record CreateOrderQrCodeItemCommand(

        String title,
        String unitMeasure,
        Double unitPrice,
        Integer quantity,
        Double totalAmount,
        String description) {

    public static CreateOrderQrCodeItemCommand with(
            final String title,
            final String unitMeasure,
            final Double unitPrice,
            final Integer quantity,
            final Double totalAmount,
            final String description
    ) {
        return new CreateOrderQrCodeItemCommand(
                title,
                unitMeasure,
                unitPrice,
                quantity,
                totalAmount,
                description
        );
    }
}
