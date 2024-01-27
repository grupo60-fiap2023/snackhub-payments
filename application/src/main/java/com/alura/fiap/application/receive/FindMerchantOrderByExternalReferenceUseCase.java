package com.alura.fiap.application.receive;

import com.alura.fiap.domain.payments.MerchantOrder;
import com.alura.fiap.domain.payments.MerchantOrderPaymentGateway;

import java.util.List;
import java.util.Optional;

public class FindMerchantOrderByExternalReferenceUseCase {

    private final MerchantOrderPaymentGateway merchantOrderPaymentGateway;

    public FindMerchantOrderByExternalReferenceUseCase(MerchantOrderPaymentGateway merchantOrderPaymentGateway) {
        this.merchantOrderPaymentGateway = merchantOrderPaymentGateway;
    }

    public Optional<List<MerchantOrder>> execute(String externalReference){
        return this.merchantOrderPaymentGateway.findMerchantOrderPaymentByExternalReference(externalReference);
    }
}
