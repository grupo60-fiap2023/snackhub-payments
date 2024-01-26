package com.alura.fiap.application;

import com.alura.fiap.domain.payments.MerchantOrder;
import com.alura.fiap.domain.payments.Payment;

import java.math.BigDecimal;
import java.util.List;

public record MerchantOrderOutput(Long orderId,
                                  String status,
                                  String externalReference,
                                  List<Payment> payment,
                                  String notificationUrl,
                                  BigDecimal totalAmount) {

    public static MerchantOrderOutput from(final MerchantOrder merchantOrder) {
        return new MerchantOrderOutput(
                merchantOrder.orderId(),
                merchantOrder.status(),
                merchantOrder.externalReference(),
                merchantOrder.payment(),
                merchantOrder.notificationUrl(),
                merchantOrder.totalAmount());
    }
}
