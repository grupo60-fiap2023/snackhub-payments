package com.alura.fiap.domain.payments;

public record OrderQrCodeItem(String title, String unitMeasure, Double unitPrice, Integer quantity,
                              Double totalAmount, String description) {

    public static OrderQrCodeItem with(
            final String title,
            final String unitMeasure,
            final Double unitPrice,
            final Integer quantity,
            final Double totalAmount,
            final String description) {
        return new OrderQrCodeItem(
                title,
                unitMeasure,
                unitPrice,
                quantity,
                totalAmount,
                description);
    }


}
