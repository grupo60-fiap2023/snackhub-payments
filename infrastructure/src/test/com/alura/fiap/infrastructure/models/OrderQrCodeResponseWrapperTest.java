package com.alura.fiap.infrastructure.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderQrCodeResponseWrapperTest {

    @Test
    public void testOrderQrCodeResponseWrapper() {
        // Given
        byte[] mediaType = new byte[]{1, 2, 3};

        // When
        OrderQrCodeResponseWrapper wrapper = new OrderQrCodeResponseWrapper(mediaType);

        // Then
        assertNotNull(wrapper);
        assertArrayEquals(mediaType, wrapper.getMediaType());
    }

    @Test
    public void testStaticFactoryMethod() {
        // Given
        byte[] mediaType = new byte[]{4, 5, 6};

        // When
        OrderQrCodeResponseWrapper wrapper = OrderQrCodeResponseWrapper.with(mediaType);

        // Then
        assertNotNull(wrapper);
        assertArrayEquals(mediaType, wrapper.getMediaType());
    }
}
