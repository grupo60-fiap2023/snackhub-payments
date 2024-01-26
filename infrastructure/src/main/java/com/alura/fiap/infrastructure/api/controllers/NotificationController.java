package com.alura.fiap.infrastructure.api.controllers;

import com.alura.fiap.application.create.MerchantOrderPaymentUseCase;
import com.alura.fiap.infrastructure.api.NotificationAPI;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController implements NotificationAPI {

    private final MerchantOrderPaymentUseCase merchantOrderPaymentUseCase;

    public NotificationController(MerchantOrderPaymentUseCase merchantOrderPaymentUseCase) {
        this.merchantOrderPaymentUseCase = merchantOrderPaymentUseCase;
    }

    @Override
    public ResponseEntity<?> notification(Long id, String topic) throws MPException, MPApiException {
        merchantOrderPaymentUseCase.execute(id, topic);
        return ResponseEntity.ok().build();
    }
}
