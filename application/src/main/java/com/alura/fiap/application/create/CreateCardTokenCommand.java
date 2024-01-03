package com.alura.fiap.application.create;

public record CreateCardTokenCommand(
        String cardNumber,
        CardHolderCommand cardHolderCommand,
        String security,
        Integer expirationMonth,
        String expirationYear
) {

    public static CreateCardTokenCommand with(
            final String cardNumber,
            final CardHolderCommand cardHolderCommand,
            final String security,
            final Integer expirationMonth,
            final String expirationYear
    ) {
        return new CreateCardTokenCommand(
                cardNumber,
                cardHolderCommand,
                security,
                expirationMonth,
                expirationYear);
    }
}
