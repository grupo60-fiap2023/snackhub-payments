package com.alura.fiap.infrastructure.configuration.usecases;


import com.alura.fiap.application.create.CreateOrderQrCodeUseCase;
import com.alura.fiap.domain.payments.OrderQrCodeGateway;
import org.junit.experimental.categories.Categories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Categories.ExcludeCategory
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
