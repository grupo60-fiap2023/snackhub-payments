package com.alura.fiap.infrastructure.configuration.usecases;


import com.alura.fiap.application.create.CreatePaymentUseCase;
import com.alura.fiap.domain.payments.PaymentGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentUseCaseConfig {

    private final PaymentGateway paymentGateway;

    public PaymentUseCaseConfig(final PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    @Bean
    public CreatePaymentUseCase createPaymentUseCase() {
        return new CreatePaymentUseCase(paymentGateway);
    }
}
