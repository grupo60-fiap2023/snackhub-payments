package com.alura.fiap.infrastructure.api.controllers;

import com.alura.fiap.application.create.MerchantOrderPaymentUseCase;
import com.alura.fiap.infrastructure.api.NotificationAPI;
import com.alura.fiap.infrastructure.models.NotificationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController implements NotificationAPI {

    private final MerchantOrderPaymentUseCase merchantOrderPaymentUseCase;

    public NotificationController(MerchantOrderPaymentUseCase merchantOrderPaymentUseCase) {
        this.merchantOrderPaymentUseCase = merchantOrderPaymentUseCase;
    }

    @Override
    public ResponseEntity<NotificationResponse> notification(Long id, String topic) {
        merchantOrderPaymentUseCase.execute(id, topic);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}