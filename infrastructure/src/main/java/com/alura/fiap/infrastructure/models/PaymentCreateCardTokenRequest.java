package com.alura.fiap.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.jetbrains.annotations.NotNull;

public record PaymentCreateCardTokenRequest(

        @JsonProperty("card_number") @NotNull @Schema(description = "Número do cartão", example = "4235647728025682")
        String cardNumber,

        @JsonProperty("cardholder") @NotNull @Schema(description = "Titular do Cartão")
        PaymentCardHolderRequest paymentCardHolderRequest,

        @JsonProperty("security_code") @NotNull @Schema(description = "Código do cartão", example = "123")
        String securityCode,

        @JsonProperty("expiration_month") @NotNull @Schema(description = "Mes de expiração", example = "11")
        Integer expirationMonth,

        @JsonProperty("expiration_year") @NotNull @Schema(description = "Ano de expiração", example = "2025")
        String expirationYear
) {
}
