package com.alura.fiap.domain.payments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderQrCodeItemTest {

    @Test
    void testOrderQrCodeItemCreation() {
        // Given
        String title = "Product Title";
        String unitMeasure = "Each";
        Double unitPrice = 10.0;
        Integer quantity = 5;
        Double totalAmount = 50.0;

        // When
        OrderQrCodeItem orderQrCodeItem = new OrderQrCodeItem(title, unitMeasure, unitPrice, quantity, totalAmount);

        // Then
        assertEquals(title, orderQrCodeItem.title());
        assertEquals(unitMeasure, orderQrCodeItem.unitMeasure());
        assertEquals(unitPrice, orderQrCodeItem.unitPrice());
        assertEquals(quantity, orderQrCodeItem.quantity());
        assertEquals(totalAmount, orderQrCodeItem.totalAmount());
    }
}
