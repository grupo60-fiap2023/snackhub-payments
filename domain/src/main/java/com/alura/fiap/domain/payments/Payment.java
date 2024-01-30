package com.alura.fiap.domain.payments;

import org.junit.experimental.categories.Categories;

import java.math.BigDecimal;


@Categories.ExcludeCategory
public record Payment(
        Long paymentId,
        BigDecimal transactionAmount,
        BigDecimal totalPaidAmount,
        BigDecimal shippingCost,
        String currencyId,
        String status,
        String statusDetails,
        String operationType,
        String dateApproved,
        String dateCreated,
        String lastModified,
        BigDecimal amountRefunded) {

    public static Payment with(
            final Long paymentId,
            final BigDecimal transactionAmount,
            final BigDecimal totalPaidAmount,
            final BigDecimal shippingCost,
            final String currencyId,
            final String status,
            final String statusDetails,
            final String operationType,
            final String dateApproved,
            final String dateCreated,
            final String lastModified,
            final BigDecimal amountRefunded
    ) {
        return new Payment(
                paymentId,
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