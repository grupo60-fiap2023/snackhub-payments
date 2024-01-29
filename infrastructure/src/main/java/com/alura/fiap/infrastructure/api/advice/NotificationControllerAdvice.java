package com.alura.fiap.infrastructure.api.advice;

import com.alura.fiap.infrastructure.api.controllers.NotificationController;
import com.mercadopago.exceptions.MPApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = NotificationController.class)
public class NotificationControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(NotificationControllerAdvice.class);

    @ExceptionHandler(MPApiException.class)
    @ResponseStatus
    public ResponseEntity<String> handleMPApiException(MPApiException ex) {
        logger.error("Error calling MercadoPago API", ex);
        return ResponseEntity.status(500).body("Error calling MercadoPago API. Check API integration.");
    }
}
