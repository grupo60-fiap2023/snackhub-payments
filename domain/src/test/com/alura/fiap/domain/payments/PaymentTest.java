package com.alura.fiap.domain.payments;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentTest {

    @Test
    void testPaymentWith() {
        // Given
        Long paymentId = 12345L;
        BigDecimal transactionAmount = new BigDecimal("100.00");
        BigDecimal totalPaidAmount = new BigDecimal("95.00");
        BigDecimal shippingCost = new BigDecimal("5.00");
        String currencyId = "USD";
        String status = "APPROVED";
        String statusDetails = "DETAILS";
        String operationType = "SALE";
        String dateApproved = "2024-01-30";
        String dateCreated = "2024-01-01";
        String lastModified = "2024-01-30";
        BigDecimal amountRefunded = new BigDecimal("0.00");

        // When
        Payment payment = Payment.with(
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
                amountRefunded
        );

        // Then
        assertEquals(paymentId, payment.paymentId());
        assertEquals(transactionAmount, payment.transactionAmount());
        assertEquals(totalPaidAmount, payment.totalPaidAmount());
        assertEquals(shippingCost, payment.shippingCost());
        assertEquals(currencyId, payment.currencyId());
        assertEquals(status, payment.status());
        assertEquals(statusDetails, payment.statusDetails());
        assertEquals(operationType, payment.operationType());
        assertEquals(dateApproved, payment.dateApproved());
        assertEquals(dateCreated, payment.dateCreated());
        assertEquals(lastModified, payment.lastModified());
        assertEquals(amountRefunded, payment.amountRefunded());
    }
}
