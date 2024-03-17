package com.alura.fiap.application.receive;

import com.alura.fiap.application.execeptions.MerchantOrderNotFoundException;
import com.alura.fiap.domain.payments.OrderQrData;
import com.alura.fiap.domain.payments.MerchantOrderPaymentGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FindQrDataByOrderIdUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(FindQrDataByOrderIdUseCase.class);

    private final MerchantOrderPaymentGateway merchantOrderPaymentGateway;

    public FindQrDataByOrderIdUseCase(MerchantOrderPaymentGateway merchantOrderPaymentGateway) {
        this.merchantOrderPaymentGateway = merchantOrderPaymentGateway;
    }

    public List<OrderQrData> execute(String orderId) throws MerchantOrderNotFoundException {
        LOGGER.info("Executing FindQrDataByOrderIdUseCase for orderId: {}", orderId);
        List<OrderQrData> orderQrData = merchantOrderPaymentGateway.findQRDataPaymentByOrderId(orderId);

        if (orderQrData.isEmpty()) {
            LOGGER.warn("No orderQrData found for the orderId: {}", orderQrData);
            throw new MerchantOrderNotFoundException("No orderQrData found for the orderId: " + orderId);
        }
        LOGGER.info("Found orderQrData for the orderId: {}", orderQrData);
        return orderQrData;
    }
}
