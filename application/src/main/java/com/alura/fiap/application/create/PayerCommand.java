package com.alura.fiap.application.create;

public record PayerCommand(
        String entityType,
        String type,
        String email,
        IdentificationCommand identificationCommand
        ) {
}