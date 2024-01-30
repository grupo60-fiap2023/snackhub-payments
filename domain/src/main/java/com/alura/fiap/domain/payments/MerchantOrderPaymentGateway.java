package com.alura.fiap.domain.payments;

import org.junit.experimental.categories.Categories;

import java.util.List;

@Categories.ExcludeCategory
public interface MerchantOrderPaymentGateway {

    void saveMerchantOrderPayment(MerchantOrder merchantOrder);

    void removeMerchantOrderPayment(MerchantOrder merchantOrder);

    List<MerchantOrder> findMerchantOrderPaymentByExternalReference(String externalReference);
}
