package com.alura.fiap.domain.payments;

import java.util.List;
import java.util.Optional;

public interface MerchantOrderPaymentGateway {

    void saveMerchantOrderPayment(MerchantOrder merchantOrder);

    void removeMerchantOrderPayment(MerchantOrder merchantOrder);

    Optional<List<MerchantOrder>> findMerchantOrderPaymentByExternalReference(String externalReference);
}
