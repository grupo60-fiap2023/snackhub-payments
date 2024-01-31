package com.alura.fiap.infrastructure.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;


public record OrderQrCodeItemsRequest(

        @JsonProperty("title") @NotNull @Schema(description = "title", example = "Combo+Refil product Order aWRfcGVkaWRv")
        String title,
        @JsonProperty("unit_measure") @NotNull @Schema(description = "unitMeasure", example = "unit")
        String unitMeasure,
        @JsonProperty("unit_price") @NotNull @Schema(description = "unitPrice", example = "29.05")
        Double unitPrice,
        @JsonProperty("quantity") @NotNull @Schema(description = "quantity", example = "1")
        Integer quantity,
        @JsonProperty("total_amount") @NotNull @Schema(description = "totalAmount", example = "29.05")
        Double totalAmount
) {
}
