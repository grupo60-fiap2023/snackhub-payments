package com.alura.fiap.infrastructure.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.*;

public class OrderQrCodeItemsRequestTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Validator validator;

    public OrderQrCodeItemsRequestTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testOrderQrCodeItemsRequest() {
        // Given
        String title = "Combo+Refil product Order aWRfcGVkaWRv";
        String unitMeasure = "unit";
        Double unitPrice = 29.05;
        Integer quantity = 1;
        Double totalAmount = 29.05;

        // When
        OrderQrCodeItemsRequest request = new OrderQrCodeItemsRequest(title, unitMeasure, unitPrice, quantity, totalAmount);

        // Then
        assertNotNull(request);
        assertEquals(title, request.title());
        assertEquals(unitMeasure, request.unitMeasure());
        assertEquals(unitPrice, request.unitPrice());
        assertEquals(quantity, request.quantity());
        assertEquals(totalAmount, request.totalAmount());
    }

    @Test
    public void testJsonSerialization() throws Exception {
        // Given
        OrderQrCodeItemsRequest request = new OrderQrCodeItemsRequest(
                "Combo+Refil product Order aWRfcGVkaWRv",
                "unit",
                29.05,
                1,
                29.05
        );

        // When
        String json = objectMapper.writeValueAsString(request);

        // Then
        assertEquals("{\"title\":\"Combo+Refil product Order aWRfcGVkaWRv\",\"unit_measure\":\"unit\",\"unit_price\":29.05,\"quantity\":1,\"total_amount\":29.05}", json);
    }

    @Test
    public void testValidationSuccess() {
        // Given
        OrderQrCodeItemsRequest request = new OrderQrCodeItemsRequest(
                "Combo+Refil product Order aWRfcGVkaWRv",
                "unit",
                29.05,
                1,
                29.05
        );

        // When
        var violations = validator.validate(request);

        // Then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testValidationFailure() {
        // Given
        OrderQrCodeItemsRequest request = new OrderQrCodeItemsRequest(
                null,
                "unit",
                29.05,
                1,
                29.05
        );

        // When
        var violations = validator.validate(request);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("não deve ser nulo", violations.iterator().next().getMessage());
    }
}
