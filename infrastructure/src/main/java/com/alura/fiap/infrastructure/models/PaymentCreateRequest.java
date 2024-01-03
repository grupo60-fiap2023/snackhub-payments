package com.alura.fiap.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public record PaymentCreateRequest(
        @JsonProperty("description") @NotNull @Schema(description = "Descrição do pagamento", example = "Payment for product test status APRO")
        String description,

        @JsonProperty("installments") @NotNull @Schema(description = "Parcelamento", example = "1")
        Integer installments,

        @JsonProperty("payer") @NotNull @Schema(description = "Dados do comprador")
        PaymentPayerRequest payer,

        @JsonProperty("payment_method_id") @NotNull @Schema(description = "Bandeira do cartão de crédito", example = "visa")
        String paymentMethodId,

        @JsonProperty("token") @NotNull @Schema(description = "Token do cartão de crédito", example = "60105f4aaff921f97d373ef3b2479f55")
        String token,

        @JsonProperty("transaction_amount") @NotNull @Schema(description = "Valor da compra", example = "1.99")
        BigDecimal transactionAmount,

        @JsonProperty("issuer_id") @Schema(description = "Código emissor do cartão", example = "25")
        String issuerId
) {
}