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
        try {
            ResponseEntity<Object> executeResponse = merchantOrderPaymentUseCase.execute(id, topic);

            if (executeResponse.getStatusCode().is2xxSuccessful()) {
                Object responseBody = executeResponse.getBody();

                if (responseBody instanceof Long) {
                    NotificationResponse response = new NotificationResponse((Long) responseBody);
                    return ResponseEntity.ok(response);
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            } else {
                return ResponseEntity.status(executeResponse.getStatusCode()).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}