package com.alura.fiap.infrastructure.configuration.usecases;


import com.alura.fiap.application.create.MerchantOrderPaymentUseCase;
import com.alura.fiap.application.receive.FindMerchantOrderByExternalReferenceUseCase;
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
    public FindMerchantOrderByExternalReferenceUseCase findMerchantOrderByIdUseCase() {
        return new FindMerchantOrderByExternalReferenceUseCase(merchantOrderPaymentGateway);
    }
}
