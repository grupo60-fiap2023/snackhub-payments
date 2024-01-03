package com.alura.fiap.infrastructure.configuration.usecases;


import com.alura.fiap.application.create.CreateCardTokeUseCase;
import com.alura.fiap.domain.payments.CardTokenGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardTokenUseCaseConfig {

    private final CardTokenGateway cardTokenGateway;

    public CardTokenUseCaseConfig(final CardTokenGateway cardTokenGateway) {
        this.cardTokenGateway = cardTokenGateway;
    }

    @Bean
    public CreateCardTokeUseCase createCardTokeUseCase(){
        return new CreateCardTokeUseCase(cardTokenGateway);
    }
}
