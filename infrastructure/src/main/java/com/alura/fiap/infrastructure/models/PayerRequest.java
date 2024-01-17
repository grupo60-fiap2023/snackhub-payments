package com.alura.fiap.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.jetbrains.annotations.NotNull;

public record PayerRequest(
        @JsonProperty("entity_type") @Schema(description = "Tipo de entidade", example = "individual")
        String entityType,
        @JsonProperty("type") @Schema(description = "Tipo", example = "customer")
        String type,
        @JsonProperty("email") @Schema(description = "Email", example = "test_user_123@testuser.com")
        String email,
        @JsonProperty("identification") @NotNull @Schema(description = "Identificação com doc tipo CPF")
        PaymentIdentificationRequest identification
) {
}