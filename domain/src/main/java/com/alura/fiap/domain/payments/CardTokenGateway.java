package com.alura.fiap.domain.payments;

import com.mercadopago.resources.CardToken;

public interface CardTokenGateway {

    CardToken createCardToken(String authorization, CreateCardToken request, String publicKey);
}
