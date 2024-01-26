package com.alura.fiap.domain.payments;

public interface MerchantOrderPaymentGateway {

    void saveMerchantOrderPayment(MerchantOrder merchantOrder);

    void removeMerchantOrderPayment(MerchantOrder merchantOrder);

    MerchantOrder findMerchantOrderPayment(Long id);
}
