package com.alura.fiap.domain.payments;

public record Payer(
        String entityType,
        String type,
        String email,
        Identification identification) {
}