package com.alura.fiap.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Email;

public record PaymentPayerRequest(

        @JsonProperty("entity_type") @Schema(description = "Tipo de entidade", example = "individual")
        String entityType,

        @JsonProperty("type") @NotNull @Schema(description = "Tipo", example = "customer")
        String type,

        @Email
        @JsonProperty("email") @NotNull @Schema(description = "Email", example = "jhon@doe.com")
        String email,

        @JsonProperty("identification") @NotNull @Schema(description = "Indentificação com doc tipo CPF")
        PaymentIdentificationRequest identification) {
}
