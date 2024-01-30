package com.alura.fiap.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.experimental.categories.Categories;

@Categories.ExcludeCategory
public record NotificationResponse(
        @JsonProperty("id") Long id
) {
}
