package com.alura.fiap.application.create;

import com.alura.fiap.domain.payments.Status;

public record CardHolderCommand(
        Status name,
        IdentificationCommand identificationCommand) {
}