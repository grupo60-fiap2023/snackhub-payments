package com.alura.fiap.domain.payments;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MerchantOrderTest {

    @Test
    void testMerchantOrderCreation() {
        // Given
        Long orderId = 123L;
        String status = "Pending";
        String externalReference = "REF-123";
        List<Payment> payments = List.of(/* create Payment instances */);
        String notificationUrl = "http://example.com/notify";
        BigDecimal totalAmount = new BigDecimal("100.00");
        String title = "Order 123";
        String description = "Order 123 description";

        // When
        MerchantOrder merchantOrder = MerchantOrder.with(orderId, status, externalReference, title, description, payments, notificationUrl, totalAmount);

        // Then
        assertEquals(orderId, merchantOrder.orderId());
        assertEquals(status, merchantOrder.status());
        assertEquals(externalReference, merchantOrder.externalReference());
        assertEquals(payments, merchantOrder.payment());
        assertEquals(notificationUrl, merchantOrder.notificationUrl());
        assertEquals(totalAmount, merchantOrder.totalAmount());
    }
}