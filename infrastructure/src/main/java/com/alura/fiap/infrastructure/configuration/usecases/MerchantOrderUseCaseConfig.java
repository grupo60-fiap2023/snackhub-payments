package com.alura.fiap.infrastructure.configuration.usecases;


import com.alura.fiap.application.create.MerchantOrderPaymentUseCase;
import com.alura.fiap.application.receive.FindMerchantOrderByExternalReferenceUseCase;
import com.alura.fiap.domain.payments.MerchantOrderPaymentGateway;
import org.junit.experimental.categories.Categories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Categories.ExcludeCategory
@Configuration
public class MerchantOrderUseCaseConfig {

    private final MerchantOrderPaymentGateway merchantOrderPaymentGateway;
    private final String accessTokenSeller;


    public MerchantOrderUseCaseConfig(MerchantOrderPaymentGateway merchantOrderPaymentGateway,
                                      @Value("${access.token.seller}") String accessTokenSeller) {
        this.merchantOrderPaymentGateway = merchantOrderPaymentGateway;
        this.accessTokenSeller = accessTokenSeller;
    }

    @Bean
    public MerchantOrderPaymentUseCase merchantOrderPaymentUseCase() {
        return new MerchantOrderPaymentUseCase(merchantOrderPaymentGateway, accessTokenSeller);
    }

    @Bean
    public FindMerchantOrderByExternalReferenceUseCase findMerchantOrderByIdUseCase() {
        return new FindMerchantOrderByExternalReferenceUseCase(merchantOrderPaymentGateway);
    }
}
