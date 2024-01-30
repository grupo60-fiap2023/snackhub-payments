package com.alura.fiap.infrastructure.persistence;

import com.alura.fiap.domain.payments.Payment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DocumentPaymentTest {

    @Test
    void toAggregate_ShouldConvertToPaymentAggregate() {
        // Arrange
        Long paymentId = 1L;
        BigDecimal transactionAmount = new BigDecimal("100.00");
        BigDecimal totalPaidAmount = new BigDecimal("100.00");
        BigDecimal shippingCost = new BigDecimal("10.00");
        String currencyId = "USD";
        String status = "approved";
        String statusDetails = "Details";
        String operationType = "payment";
        String dateApproved = "2024-01-30T12:00:00Z";
        String dateCreated = "2024-01-30T11:00:00Z";
        String lastModified = "2024-01-30T12:30:00Z";
        BigDecimal amountRefunded = new BigDecimal("0.00");

        DocumentPayment documentPayment = new DocumentPayment(
                paymentId, transactionAmount, totalPaidAmount, shippingCost, currencyId, status,
                statusDetails, operationType, dateApproved, dateCreated, lastModified, amountRefunded);

        // Act
        Payment payment = documentPayment.toAggregate();

        // Assert
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
