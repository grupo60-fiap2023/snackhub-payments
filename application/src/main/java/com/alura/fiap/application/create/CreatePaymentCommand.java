package com.alura.fiap.application.create;

import java.math.BigDecimal;

public record CreatePaymentCommand(
        String description,
        Integer installments,
        PayerCommand payerCommand,
        String paymentMethodId,
        String token,
        BigDecimal transactionAmount,
        Integer issuerId
) {

    public static CreatePaymentCommand with(
            final String description,
            final Integer installments,
            final PayerCommand payerCommand,
            final String paymentMethodId,
            final String token,
            final BigDecimal transactionAmount,
            final Integer issuerId
    ) {
        return new CreatePaymentCommand(
                description,
                installments,
                payerCommand,
                paymentMethodId,
                token,
                transactionAmount,
                issuerId
        );
    }
}
