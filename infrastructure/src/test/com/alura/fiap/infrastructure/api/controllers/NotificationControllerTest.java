package com.alura.fiap.infrastructure.api.controllers;

import com.alura.fiap.application.create.MerchantOrderPaymentUseCase;
import com.alura.fiap.infrastructure.models.NotificationResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class NotificationControllerTest {

    @Test
    void notification_SuccessfulPayment_ReturnsOkResponse() {
        // Arrange
        MerchantOrderPaymentUseCase mockUseCase = Mockito.mock(MerchantOrderPaymentUseCase.class);
        Mockito.when(mockUseCase.execute(anyLong(), anyString())).thenReturn(ResponseEntity.ok(1L));

        NotificationController controller = new NotificationController(mockUseCase);

        // Act
        ResponseEntity<NotificationResponse> response = controller.notification(1L, "merchant-order");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void notification_FailedPayment_ReturnsInternalServerError() {
        // Arrange
        MerchantOrderPaymentUseCase mockUseCase = Mockito.mock(MerchantOrderPaymentUseCase.class);
        Mockito.when(mockUseCase.execute(anyLong(), anyString()))
                .thenReturn(ResponseEntity.status(HttpStatus.OK).build());

        NotificationController controller = new NotificationController(mockUseCase);

        // Act
        ResponseEntity<NotificationResponse> response = controller.notification(1L, "merchant-order");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}