package com.alura.fiap.domain.payments;


import java.math.BigDecimal;

public class CreatePayment {
    String description;
    Integer installments;
    Payer payer;
    String paymentMethodId;
    String token;
    BigDecimal transactionAmount;
    Integer issuerId;

    public CreatePayment(String description, Integer installments, Payer payer, String paymentMethodId, String token, BigDecimal transactionAmount, Integer issuerId) {
        this.description = description;
        this.installments = installments;
        this.payer = payer;
        this.paymentMethodId = paymentMethodId;
        this.token = token;
        this.transactionAmount = transactionAmount;
        this.issuerId = issuerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Integer getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(Integer issuerId) {
        this.issuerId = issuerId;
    }
}