package com.alura.fiap.infrastructure.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PaymentIdentificationRequestTest {

    @Test
    public void testPaymentIdentificationRequest() {
        // Given
        String type = "CPF";
        String number = "12345678909";

        // When
        PaymentIdentificationRequest identificationRequest = new PaymentIdentificationRequest(type, number);

        // Then
        assertNotNull(identificationRequest);
        assertEquals(type, identificationRequest.type());
        assertEquals(number, identificationRequest.number());
    }
}
