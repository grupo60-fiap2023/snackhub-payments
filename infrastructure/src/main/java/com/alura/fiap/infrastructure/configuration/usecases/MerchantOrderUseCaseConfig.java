package com.alura.fiap.infrastructure.configuration.usecases;


import com.alura.fiap.application.create.MerchantOrderPaymentUseCase;
import com.alura.fiap.application.receive.FindMerchantOrderByIdUseCase;
import com.alura.fiap.domain.payments.MerchantOrderPaymentGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MerchantOrderUseCaseConfig {

    private final MerchantOrderPaymentGateway merchantOrderPaymentGateway;

    public MerchantOrderUseCaseConfig(MerchantOrderPaymentGateway merchantOrderPaymentGateway) {
        this.merchantOrderPaymentGateway = merchantOrderPaymentGateway;
    }

    @Bean
    public MerchantOrderPaymentUseCase merchantOrderPaymentUseCase() {
        return new MerchantOrderPaymentUseCase(merchantOrderPaymentGateway);
    }

    @Bean
    public FindMerchantOrderByIdUseCase findMerchantOrderByIdUseCase() {
        return new FindMerchantOrderByIdUseCase(merchantOrderPaymentGateway);
    }
}
