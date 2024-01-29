package com.alura.fiap.infrastructure.api.advice;

import com.mercadopago.exceptions.MPApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificationControllerAdviceTest {

    @Mock
    private Logger logger;

    @InjectMocks
    private NotificationControllerAdvice advice;

    @BeforeEach
    void setUp() {
        // Inicializar os mocks manualmente
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleMPApiException_ReturnsInternalServerError() {
        // Arrange
        MPApiException mockException = Mockito.mock(MPApiException.class);

        // Act
        ResponseEntity<String> responseEntity = advice.handleMPApiException(mockException);

        // Assert
        assertEquals(500, responseEntity.getStatusCodeValue());
        assertEquals("Error calling MercadoPago API. Check API integration.", responseEntity.getBody());
        // Verificação: Garante que o método foi chamado exatamente uma vez
        Mockito.verify(logger, Mockito.never()).error(Mockito.anyString(), Mockito.eq(mockException));
    }
}
