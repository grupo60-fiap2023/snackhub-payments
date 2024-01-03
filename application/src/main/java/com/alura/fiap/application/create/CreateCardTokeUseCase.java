package com.alura.fiap.application.create;

import com.alura.fiap.application.AuthMPUseCase;
import com.alura.fiap.application.CardTokenOutput;
import com.alura.fiap.domain.payments.CardTokenGateway;
import com.alura.fiap.domain.payments.CreateCardToken;
import com.alura.fiap.domain.payments.Identification;
import com.alura.fiap.domain.payments.CardHolder;

import java.util.Objects;

public class CreateCardTokeUseCase extends AuthMPUseCase<CreateCardTokenCommand, CardTokenOutput> {

    private final CardTokenGateway cardTokenGateway;

    public CreateCardTokeUseCase(CardTokenGateway cardTokenGateway) {
        this.cardTokenGateway = Objects.requireNonNull(cardTokenGateway);
    }

    @Override
    public CardTokenOutput execute(String authorization, CreateCardTokenCommand inputObject, String publicKey) {

        var cardHolderIdentification = new Identification(inputObject.cardHolderCommand().identificationCommand().type(), inputObject.cardHolderCommand().identificationCommand().number());
        var cardHolderStatus = new CardHolder(inputObject.cardHolderCommand().name(), cardHolderIdentification);
        var cardToken = new CreateCardToken(inputObject.cardNumber(), cardHolderStatus,
                inputObject.security(), inputObject.expirationMonth(), inputObject.expirationYear());
        return CardTokenOutput.from(cardTokenGateway.createCardToken(authorization, cardToken, publicKey));
    }
}