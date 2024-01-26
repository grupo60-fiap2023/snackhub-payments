package com.alura.fiap.infrastructure.persistence;

import com.alura.fiap.domain.payments.Payment;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
public class DocumentPayment {

    @Id
    private Long id;
    @Indexed(unique = true)
    private final Long paymentId;
    private final BigDecimal transactionAmount;
    private final BigDecimal totalPaidAmount;
    private final BigDecimal shippingCost;
    private final String currencyId;
    private final String status;
    private final String statusDetails;
    private final String operationType;
    private final String dateApproved;
    private final String dateCreated;
    private final String lastModified;
    private final BigDecimal amountRefunded;

    public DocumentPayment(Long paymentId,
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
        this.paymentId = paymentId;
        this.transactionAmount = transactionAmount;
        this.totalPaidAmount = totalPaidAmount;
        this.shippingCost = shippingCost;
        this.currencyId = currencyId;
        this.status = status;
        this.statusDetails = statusDetails;
        this.operationType = operationType;
        this.dateApproved = dateApproved;
        this.dateCreated = dateCreated;
        this.lastModified = lastModified;
        this.amountRefunded = amountRefunded;
    }

    public Payment toAggregate() {
        return Payment.with(getPaymentId(), getTransactionAmount(), getTotalPaidAmount(), getShippingCost(), getCurrencyId(),
                getStatus(), getStatusDetails(), getOperationType(), getDateApproved(), getDateCreated(), getLastModified(), getAmountRefunded());
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public BigDecimal getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusDetails() {
        return statusDetails;
    }

    public String getOperationType() {
        return operationType;
    }

    public String getDateApproved() {
        return dateApproved;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getLastModified() {
        return lastModified;
    }

    public BigDecimal getAmountRefunded() {
        return amountRefunded;
    }
}
