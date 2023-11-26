package com.alura.fiap.infrastructure.configuration.usecases;


import com.alura.fiap.application.create.CreateOrderQrCodeUseCase;
import com.alura.fiap.domain.payments.OrderQrCodeGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderQrCodeUseCaseConfig {

    private final OrderQrCodeGateway orderQrCodeGateway;

    public OrderQrCodeUseCaseConfig(final OrderQrCodeGateway orderQrCodeGateway) {
        this.orderQrCodeGateway = orderQrCodeGateway;
    }

    @Bean
    public CreateOrderQrCodeUseCase createOrderQrCodeUseCase() {
        return new CreateOrderQrCodeUseCase(orderQrCodeGateway);
    }
}
