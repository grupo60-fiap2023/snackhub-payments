package com.alura.fiap.domain.payments;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderQrCodeTest {

    @Test
    void testOrderQrCodeCreation() {
        // Given
        String externalReference = "REF-456";
        String title = "Order Title";
        String notificationUrl = "http://example.com/notify";
        Double totalAmount = 150.0;
        List<OrderQrCodeItem> items = List.of(
                /* create OrderQrCodeItem instances */);
        OrderQrCodeCashOut cashOut = new OrderQrCodeCashOut(
                new OrderQrCodeCashOut(0.0).amount());
        String description = "Order Description";

        // When
        OrderQrCode orderQrCode = new OrderQrCode(externalReference, title, notificationUrl, totalAmount, items, cashOut, description);

        // Then
        assertEquals(externalReference, orderQrCode.externalReference());
        assertEquals(title, orderQrCode.title());
        assertEquals(notificationUrl, orderQrCode.notificationUrl());
        assertEquals(totalAmount, orderQrCode.totalAmount());
        assertEquals(items, orderQrCode.items());
        assertEquals(cashOut, orderQrCode.cashOut());
        assertEquals(description, orderQrCode.description());
    }
}
