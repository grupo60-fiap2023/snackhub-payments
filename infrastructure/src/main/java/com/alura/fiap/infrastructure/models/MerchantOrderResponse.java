package com.alura.fiap.infrastructure.models;

import com.alura.fiap.domain.payments.Payment;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public record MerchantOrderResponse(@JsonProperty("id") Long id,
                                    @JsonProperty("status") String status,
                                    @JsonProperty("externalReference") String externalReference,
                                    @JsonProperty("payment") List<Payment> payment,
                                    @JsonProperty("notificationUrl") String notificationUrl,
                                    @JsonProperty("totalAmount") BigDecimal totalAmount) {
}
