package com.alura.fiap.application.receive;

import com.alura.fiap.domain.payments.MerchantOrder;
import com.alura.fiap.domain.payments.MerchantOrderPaymentGateway;

public class FindMerchantOrderByIdUseCase {

    private final MerchantOrderPaymentGateway merchantOrderPaymentGateway;

    public FindMerchantOrderByIdUseCase(MerchantOrderPaymentGateway merchantOrderPaymentGateway) {
        this.merchantOrderPaymentGateway = merchantOrderPaymentGateway;
    }

    public MerchantOrder execute(Long id){
        return this.merchantOrderPaymentGateway.findMerchantOrderPayment(id);
    }
}
