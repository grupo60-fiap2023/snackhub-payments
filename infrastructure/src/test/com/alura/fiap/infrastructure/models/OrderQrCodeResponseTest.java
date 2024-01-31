package com.alura.fiap.infrastructure.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderQrCodeResponseTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testOrderQrCodeResponse() {
        // Given
        String inStoreOrderId = "9fb91863-2b00-4333-92ae-a70011453722";
        String qrData = "00020101021243650016COM.MERCADOLIBRE0201306369fb91863-2b00-4333-92ae-a700114537225204000053039865802BR5912Marlon Costa6009SAO PAULO62070503***6304EBF6";

        // When
        OrderQrCodeResponse response = new OrderQrCodeResponse(inStoreOrderId, qrData);

        // Then
        assertNotNull(response);
        assertEquals(inStoreOrderId, response.inStoreOrderId());
        assertEquals(qrData, response.qrData());
    }

    @Test
    public void testJsonSerialization() throws Exception {
        // Given
        OrderQrCodeResponse response = new OrderQrCodeResponse(
                "9fb91863-2b00-4333-92ae-a70011453722",
                "00020101021243650016COM.MERCADOLIBRE0201306369fb91863-2b00-4333-92ae-a700114537225204000053039865802BR5912Marlon Costa6009SAO PAULO62070503***6304EBF6"
        );

        // When
        String json = objectMapper.writeValueAsString(response);

        // Then
        assertEquals("{\"in_store_order_id\":\"9fb91863-2b00-4333-92ae-a70011453722\",\"qr_data\":\"00020101021243650016COM.MERCADOLIBRE0201306369fb91863-2b00-4333-92ae-a700114537225204000053039865802BR5912Marlon Costa6009SAO PAULO62070503***6304EBF6\"}", json);
    }
}
