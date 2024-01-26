package com.alura.fiap.application.create;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record MerchantOrderPaymentCommand(
        Long id,
        BigDecimal transactionAmount,
        BigDecimal totalPaidAmount,
        BigDecimal shippingCost,
        String currencyId,
        String status,
        String statusDetails,
        String operationType,
        OffsetDateTime dateApproved,
        OffsetDateTime dateCreated,
        OffsetDateTime lastModified,
        BigDecimal amountRefunded) {

    public static MerchantOrderPaymentCommand with(
            final Long id,
            final BigDecimal transactionAmount,
            final BigDecimal totalPaidAmount,
            final BigDecimal shippingCost,
            final String currencyId,
            final String status,
            final String statusDetails,
            final String operationType,
            final OffsetDateTime dateApproved,
            final OffsetDateTime dateCreated,
            final OffsetDateTime lastModified,
            final BigDecimal amountRefunded
    ) {
        return new MerchantOrderPaymentCommand(
                id,
                transactionAmount,
                totalPaidAmount,
                shippingCost,
                currencyId,
                status,
                statusDetails,
                operationType,
                dateApproved,
                dateCreated,
                lastModified,
                amountRefunded);
    }

}
