package com.alura.fiap.infrastructure.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomOrderQrCodeExceptionTest {

    @Test
    public void testCustomOrderQrCodeException() {
        String errorMessage = "Test error message";
        CustomOrderQrCodeException exception = new CustomOrderQrCodeException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }
}
