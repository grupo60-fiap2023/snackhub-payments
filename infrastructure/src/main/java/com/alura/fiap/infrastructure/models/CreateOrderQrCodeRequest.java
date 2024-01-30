package com.alura.fiap.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.jetbrains.annotations.NotNull;
import org.junit.experimental.categories.Categories;

import java.util.List;

@Categories.ExcludeCategory
public record CreateOrderQrCodeRequest(
        @JsonProperty("external_reference") @Schema(description = "Referencia do pedido", example = "aWRfcGVkaWRv")
        String externalReference,
        @JsonProperty("title") @Schema(description = "Titulo da Pedido", example = "Product Order aWRfcGVkaWRv")
        String title,
        @JsonProperty("items") @NotNull @Schema(description = "Lista dos items do pedido")
        List<OrderQrCodeItemsRequest> items,
        @JsonProperty("total_amount") @NotNull @Schema(description = "Valor total do pedido", example = "29.05")
        Double totalAmount,
        @JsonProperty("cash_out") @NotNull @Schema(description = "Valor de troco")
        OrderQrCodeCashOutRequest cashOut,
        @JsonProperty("notification_url") @Schema(description = "URL webhook notification", example = "https://snackhubpay-mercadopago.ultrahook.com")
        String notificationUrl,
        @JsonProperty("description") @NotNull @Schema(description = "Descrição do pedido", example = "Combo+Refil")
        String description
) {
}
