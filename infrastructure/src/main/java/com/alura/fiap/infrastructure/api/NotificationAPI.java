package com.alura.fiap.infrastructure.api;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/notification")
@Tag(name = "Mercado Pago Webhooks Notifications")
public interface NotificationAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Receive notifications merchant order and payment", security = @SecurityRequirement(name = "bearerAuth"))
    ResponseEntity<?> notification(@RequestParam @Schema(hidden = true) Long id,
                                   @RequestParam @Schema(hidden = true) String topic) throws MPException, MPApiException;
}