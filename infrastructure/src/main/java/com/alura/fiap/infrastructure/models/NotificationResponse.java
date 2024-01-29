package com.alura.fiap.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NotificationResponse(
        @JsonProperty("id") Long id
) {
}
