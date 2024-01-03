package com.alura.fiap.application;


import com.mercadopago.resources.CardToken;

public record CardTokenOutput(CardToken cardToken) {

    public static CardTokenOutput from(CardToken cardToken) {
        return new CardTokenOutput(cardToken);
    }
}
