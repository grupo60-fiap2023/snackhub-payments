package com.alura.fiap.application.create;

import com.alura.fiap.domain.payments.Payment;

import java.math.BigDecimal;
import java.util.List;

public record MerchantOrderQrCodeCommand(
        Long id,
        String status,
        String externalReference,
        List<Payment> merchantOrderPayment,
        String notificationUrl,
        BigDecimal totalAmount
) {

    public static MerchantOrderQrCodeCommand with(
            final Long id,
            final String status,
            final String externalReference,
            final List<Payment> orderPaymentCommandList,
            final String notificationUrl,
            final BigDecimal totalAmount
    ) {
        return new MerchantOrderQrCodeCommand(
                id,
                status,
                externalReference,
                orderPaymentCommandList,
                notificationUrl,
                totalAmount);
    }
}
