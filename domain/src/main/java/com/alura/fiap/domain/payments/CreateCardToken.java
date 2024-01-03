package com.alura.fiap.domain.payments;


public class CreateCardToken {

    String cardNumber;
    CardHolder cardHolder;
    String securityCode;
    Integer expirationMonth;
    String expirationYear;

    public CreateCardToken(String cardNumber, CardHolder cardHolder, String securityCode, Integer expirationMonth, String expirationYear) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.securityCode = securityCode;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CardHolder getPaymentCardHolder() {
        return cardHolder;
    }

    public void setPaymentCardHolder(CardHolder cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public Integer getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(Integer expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public String getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }
}