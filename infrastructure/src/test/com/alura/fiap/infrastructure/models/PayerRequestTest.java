package com.alura.fiap.infrastructure.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PayerRequestTest {

    @Test
    public void testPayerRequest() {
        // Given
        String entityType = "individual";
        String type = "customer";
        String email = "test_user_123@testuser.com";
        PaymentIdentificationRequest identification = new PaymentIdentificationRequest("CPF", "12345678901");

        // When
        PayerRequest payerRequest = new PayerRequest(entityType, type, email, identification);

        // Then
        assertNotNull(payerRequest);
        assertEquals(entityType, payerRequest.entityType());
        assertEquals(type, payerRequest.type());
        assertEquals(email, payerRequest.email());
        assertEquals(identification, payerRequest.identification());
    }
}
