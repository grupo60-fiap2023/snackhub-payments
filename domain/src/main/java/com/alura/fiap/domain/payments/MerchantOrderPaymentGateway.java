package com.alura.fiap.domain.payments;

import java.util.List;

public interface MerchantOrderPaymentGateway {

    void saveMerchantOrderPayment(MerchantOrder merchantOrder);

    void removeMerchantOrderPayment(MerchantOrder merchantOrder);

    List<MerchantOrder> findMerchantOrderPaymentByExternalReference(String externalReference);
}
