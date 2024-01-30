package com.alura.fiap.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.junit.experimental.categories.Categories;

import javax.validation.constraints.NotNull;

@Categories.ExcludeCategory
public record OrderQrCodeCashOutRequest(
        @JsonProperty("amount") @NotNull @Schema(description = "amount", example = "0") Double amount) {

}
