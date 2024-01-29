package com.alura.fiap.application.execeptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MerchantOrderNotFoundExceptionTest {

    @Test
    void constructorShouldSetMessage() {
        // Arrange
        String errorMessage = "Order not found";

        // Act
        MerchantOrderNotFoundException exception = new MerchantOrderNotFoundException(errorMessage);

        // Assert
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void throwExceptionWithMessage() {
        // Arrange
        String errorMessage = "Order not found";

        // Act and Assert
        try {
            throw new MerchantOrderNotFoundException(errorMessage);
        } catch (MerchantOrderNotFoundException exception) {
            assertEquals(errorMessage, exception.getMessage());
        }
    }

}