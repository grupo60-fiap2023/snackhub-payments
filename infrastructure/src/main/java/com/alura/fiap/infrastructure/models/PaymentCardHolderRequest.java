package com.alura.fiap.infrastructure.models;

import com.alura.fiap.domain.payments.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.jetbrains.annotations.NotNull;

public record PaymentCardHolderRequest(
        @JsonProperty("name") @Schema(description = "Nome do Cartão", example = "APRO")
        Status name,
        @JsonProperty("identification") @NotNull @Schema(description = "Identificação com doc tipo CPF")
        PaymentIdentificationRequest identification) {
}