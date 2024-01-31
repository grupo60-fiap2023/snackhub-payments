package com.alura.fiap.infrastructure.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.*;

public class OrderQrCodeCashOutRequestTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Validator validator;

    public OrderQrCodeCashOutRequestTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testOrderQrCodeCashOutRequest() {
        // Given
        Double amount = 100.0;

        // When
        OrderQrCodeCashOutRequest request = new OrderQrCodeCashOutRequest(amount);

        // Then
        assertNotNull(request);
        assertEquals(amount, request.amount());
    }

    @Test
    public void testJsonSerialization() throws Exception {
        // Given
        OrderQrCodeCashOutRequest request = new OrderQrCodeCashOutRequest(50.0);

        // When
        String json = objectMapper.writeValueAsString(request);

        // Then
        assertEquals("{\"amount\":50.0}", json);
    }

    @Test
    public void testValidationSuccess() {
        // Given
        OrderQrCodeCashOutRequest request = new OrderQrCodeCashOutRequest(75.0);

        // When
        var violations = validator.validate(request);

        // Then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testValidationFailure() {
        // Given
        OrderQrCodeCashOutRequest request = new OrderQrCodeCashOutRequest(null);

        // When
        var violations = validator.validate(request);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("não deve ser nulo", violations.iterator().next().getMessage());
    }
}
