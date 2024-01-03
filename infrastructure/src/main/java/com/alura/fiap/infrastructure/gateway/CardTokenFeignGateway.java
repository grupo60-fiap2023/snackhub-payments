package com.alura.fiap.infrastructure.gateway;

import com.alura.fiap.domain.payments.CardTokenGateway;
import com.alura.fiap.domain.payments.CreateCardToken;
import com.alura.fiap.infrastructure.feign.client.MPIntegrationGateway;
import com.alura.fiap.infrastructure.models.PaymentCardHolderRequest;
import com.alura.fiap.infrastructure.models.PaymentCreateCardTokenRequest;
import com.alura.fiap.infrastructure.models.PaymentIdentificationRequest;
import com.mercadopago.resources.CardToken;
import org.springframework.stereotype.Component;

@Component
public class CardTokenFeignGateway implements CardTokenGateway {

    private final MPIntegrationGateway mpIntegrationGateway;

    public CardTokenFeignGateway(final MPIntegrationGateway mpIntegrationGateway) {
        this.mpIntegrationGateway = mpIntegrationGateway;
    }


    @Override
    public CardToken createCardToken(String authorization, CreateCardToken request, String publicKey) {

        var identification = new PaymentIdentificationRequest(request.getPaymentCardHolder().identification().type(), request.getPaymentCardHolder().identification().number());
        var cardHolder = new PaymentCardHolderRequest(request.getPaymentCardHolder().name(),
                identification);
        var createCardTokenRequest = new PaymentCreateCardTokenRequest(request.getCardNumber(), cardHolder,
                request.getSecurityCode(), request.getExpirationMonth(), request.getExpirationYear());

        return mpIntegrationGateway.createCardToken(authorization, createCardTokenRequest, publicKey).getBody();
    }
}
