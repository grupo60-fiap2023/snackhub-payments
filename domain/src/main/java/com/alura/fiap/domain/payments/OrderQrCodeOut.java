package com.alura.fiap.domain.payments;

import org.junit.experimental.categories.Categories;


@Categories.ExcludeCategory
public record OrderQrCodeOut(String inStoreOrderId, String qrData) {

    // Static factory method
    public static OrderQrCodeOut createOrderQrCodeOut(String inStoreOrderId, String qrData) {
        // You can perform additional logic if needed
        return new OrderQrCodeOut(inStoreOrderId, qrData);
    }
}
