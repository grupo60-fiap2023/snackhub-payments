package com.alura.fiap.infrastructure.api.controllers;


import com.alura.fiap.application.CardTokenOutput;
import com.alura.fiap.application.create.CardHolderCommand;
import com.alura.fiap.application.create.CreateCardTokeUseCase;
import com.alura.fiap.application.create.CreateCardTokenCommand;
import com.alura.fiap.application.create.IdentificationCommand;
import com.alura.fiap.infrastructure.api.CardTokenAPI;
import com.alura.fiap.infrastructure.models.PaymentCreateCardTokenRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardTokenAPIController implements CardTokenAPI {

    private final CreateCardTokeUseCase createCardTokeUseCase;

    public CardTokenAPIController(final CreateCardTokeUseCase createCardTokeUseCase) {
        this.createCardTokeUseCase = createCardTokeUseCase;
    }


    @Override
    public ResponseEntity<CardTokenOutput> createCardToken(String authorization, String publicKey,
                                                          PaymentCreateCardTokenRequest request) {

        var cardIdentification = new IdentificationCommand(request.paymentCardHolderRequest().identification().type(), request.paymentCardHolderRequest().identification().number());
        var cardHolderStatus = new CardHolderCommand(request.paymentCardHolderRequest().name(), cardIdentification);
        var in = new CreateCardTokenCommand(request.cardNumber(), cardHolderStatus, request.securityCode(), request.expirationMonth(), request.expirationYear());

        CardTokenOutput execute = createCardTokeUseCase.execute(authorization, in, publicKey);

        return ResponseEntity.status(HttpStatus.CREATED).body(execute);
    }
}
