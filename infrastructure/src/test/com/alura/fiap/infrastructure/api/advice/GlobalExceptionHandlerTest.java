package com.alura.fiap.infrastructure.api.advice;

import com.alura.fiap.infrastructure.exception.CustomOrderQrCodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private CustomOrderQrCodeException customOrderQrCodeException;

    @BeforeEach
    void setUp() {
        // Inicializar os mocks manualmente
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void handleCustomException_ShouldReturnBadRequest() {
        // Arrange
        String errorMessage = "Custom error message";
        when(customOrderQrCodeException.getMessage()).thenReturn(errorMessage);

        // Act
        ResponseEntity<String> responseEntity = globalExceptionHandler.handleCustomException(customOrderQrCodeException);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(errorMessage, responseEntity.getBody());
    }

    @Test
    void handleGenericException_ShouldReturnInternalServerError() {
        // Arrange
        Exception genericException = new Exception("Generic error message");

        // Act
        ResponseEntity<String> responseEntity = globalExceptionHandler.handleGenericException(genericException);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Ocorreu um erro interno, tente novamente mais tarde.", responseEntity.getBody());
    }
}
