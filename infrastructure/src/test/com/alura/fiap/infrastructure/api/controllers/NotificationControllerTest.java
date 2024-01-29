package com.alura.fiap.infrastructure.api.controllers;

import com.alura.fiap.application.create.MerchantOrderPaymentUseCase;
import com.alura.fiap.infrastructure.models.NotificationResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

class NotificationControllerTest {

    @Test
    void notification_SuccessfulPayment_ReturnsOkResponse() {
        // Arrange
        MerchantOrderPaymentUseCase mockUseCase = Mockito.mock(MerchantOrderPaymentUseCase.class);
        Mockito.when(mockUseCase.execute(anyLong(), anyString()))
                .thenReturn(ResponseEntity.ok(123L)); // Simula um pagamento bem-sucedido

        NotificationController controller = new NotificationController(mockUseCase);

        // Act
        ResponseEntity<NotificationResponse> response = controller.notification(1L, "merchant-order");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(123L, Objects.requireNonNull(response.getBody()).id());
    }

    @Test
    void notification_FailedPayment_ReturnsInternalServerError() {
        // Arrange
        MerchantOrderPaymentUseCase mockUseCase = Mockito.mock(MerchantOrderPaymentUseCase.class);
        Mockito.when(mockUseCase.execute(anyLong(), anyString()))
                .thenReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());

        NotificationController controller = new NotificationController(mockUseCase);

        // Act
        ResponseEntity<NotificationResponse> response = controller.notification(1L, "merchant-order");

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    // Adicione mais testes para outros cenários conforme necessário
}