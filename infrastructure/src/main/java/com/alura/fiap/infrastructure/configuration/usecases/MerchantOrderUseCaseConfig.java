package com.alura.fiap.infrastructure.configuration.usecases;


import com.alura.fiap.application.create.MerchantOrderPaymentUseCase;
import com.alura.fiap.application.receive.FindMerchantOrderByExternalReferenceUseCase;
import com.alura.fiap.application.receive.FindQrDataByOrderIdUseCase;
import com.alura.fiap.domain.payments.MerchantOrderPaymentGateway;
import com.alura.fiap.domain.payments.SQSEventPublisherGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MerchantOrderUseCaseConfig {

    private final MerchantOrderPaymentGateway merchantOrderPaymentGateway;
    private final String accessTokenSeller;

    private final SQSEventPublisherGateway sqsEventPublisherGateway;


    public MerchantOrderUseCaseConfig(MerchantOrderPaymentGateway merchantOrderPaymentGateway,
                                      @Value("${access.token.seller}") String accessTokenSeller, SQSEventPublisherGateway sqsEventPublisherGateway) {
        this.merchantOrderPaymentGateway = merchantOrderPaymentGateway;
        this.accessTokenSeller = accessTokenSeller;
        this.sqsEventPublisherGateway = sqsEventPublisherGateway;
    }

    @Bean
    public MerchantOrderPaymentUseCase merchantOrderPaymentUseCase() {
        return new MerchantOrderPaymentUseCase(merchantOrderPaymentGateway, accessTokenSeller, sqsEventPublisherGateway);
    }

    @Bean
    public FindMerchantOrderByExternalReferenceUseCase findMerchantOrderByIdUseCase() {
        return new FindMerchantOrderByExternalReferenceUseCase(merchantOrderPaymentGateway);
    }

    @Bean
    public FindQrDataByOrderIdUseCase findQrDataByOrderIdUseCase() {
        return new FindQrDataByOrderIdUseCase(merchantOrderPaymentGateway);
    }
}
