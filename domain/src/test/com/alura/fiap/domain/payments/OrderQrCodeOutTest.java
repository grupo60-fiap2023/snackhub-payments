package com.alura.fiap.domain.payments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderQrCodeOutTest {

    @Test
    void testCreateOrderQrCodeOut() {
        // Given
        String inStoreOrderId = "12345";
        String qrData = "QR_CODE_DATA";

        // When
        OrderQrCodeOut orderQrCodeOut = OrderQrCodeOut.createOrderQrCodeOut(inStoreOrderId, qrData);

        // Then
        assertEquals(inStoreOrderId, orderQrCodeOut.inStoreOrderId());
        assertEquals(qrData, orderQrCodeOut.qrData());
    }
}
