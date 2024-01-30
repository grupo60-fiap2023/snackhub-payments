package com.alura.fiap.domain.payments;

import org.junit.experimental.categories.Categories;

@Categories.ExcludeCategory
public record OrderQrCodeItem(String title, String unitMeasure, Double unitPrice, Integer quantity,
                              Double totalAmount) {

    public static OrderQrCodeItem with(
            final String title,
            final String unitMeasure,
            final Double unitPrice,
            final Integer quantity,
            final Double totalAmount) {
        return new OrderQrCodeItem(
                title,
                unitMeasure,
                unitPrice,
                quantity,
                totalAmount);
    }


}
