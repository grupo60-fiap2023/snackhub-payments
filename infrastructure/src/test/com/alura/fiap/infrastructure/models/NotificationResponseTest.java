package com.alura.fiap.infrastructure.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class NotificationResponseTest {

    @Test
    public void testNotificationResponse() {
        // Given
        long id = 123L;

        // When
        NotificationResponse notificationResponse = new NotificationResponse(id);

        // Then
        Assertions.assertThat(notificationResponse).isNotNull();
        Assertions.assertThat(notificationResponse.id()).isEqualTo(id);
    }

    @Test
    public void testJsonSerialization() throws IOException {
        // Given
        NotificationResponse notificationResponse = new NotificationResponse(456L);

        // When
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(notificationResponse);

        // Then
        Assertions.assertThat(json).isEqualTo("{\"id\":456}");

        // You may want to further validate the JSON structure or use a more specific assertion library
    }

    @Test
    public void testJsonDeserialization() throws IOException {
        // Given
        String json = "{\"id\":789}";

        // When
        ObjectMapper objectMapper = new ObjectMapper();
        NotificationResponse notificationResponse = objectMapper.readValue(json, NotificationResponse.class);

        // Then
        Assertions.assertThat(notificationResponse).isNotNull();
        Assertions.assertThat(notificationResponse.id()).isEqualTo(789L);
    }
}
