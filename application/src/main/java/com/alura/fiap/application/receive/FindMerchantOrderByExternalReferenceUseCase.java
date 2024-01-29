package com.alura.fiap.application.receive;

import com.alura.fiap.application.execeptions.MerchantOrderNotFoundException;
import com.alura.fiap.domain.payments.MerchantOrder;
import com.alura.fiap.domain.payments.MerchantOrderPaymentGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FindMerchantOrderByExternalReferenceUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(FindMerchantOrderByExternalReferenceUseCase.class);

    private final MerchantOrderPaymentGateway merchantOrderPaymentGateway;

    public FindMerchantOrderByExternalReferenceUseCase(MerchantOrderPaymentGateway merchantOrderPaymentGateway) {
        this.merchantOrderPaymentGateway = merchantOrderPaymentGateway;
    }

    public List<MerchantOrder> execute(String externalReference) throws MerchantOrderNotFoundException {
        LOGGER.info("Executing FindMerchantOrderByExternalReferenceUseCase for external reference: {}", externalReference);
        List<MerchantOrder> merchantOrders = merchantOrderPaymentGateway.findMerchantOrderPaymentByExternalReference(externalReference);

        if (merchantOrders.isEmpty()) {
            LOGGER.warn("No merchant orders found for the external reference: {}", externalReference);
            throw new MerchantOrderNotFoundException("No merchant orders found for the external reference: " + externalReference);
        }
        LOGGER.info("Found {} merchant orders for the external reference: {}", merchantOrders.size(), externalReference);
        return merchantOrders;
    }
}
