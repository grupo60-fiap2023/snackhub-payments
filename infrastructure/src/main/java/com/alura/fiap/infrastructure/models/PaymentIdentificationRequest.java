package com.alura.fiap.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.jetbrains.annotations.NotNull;

public record PaymentIdentificationRequest(
        @JsonProperty("type") @NotNull @Schema(description = "Tipo", example = "CPF")
        String type,

        // TODO Add validação CPF
        @JsonProperty("number") @NotNull @Schema(description = "Apenas Numeros CPF", example = "12345678909")
        String number) {
}
