package com.alura.fiap.domain.payments;

import java.math.BigDecimal;
import java.util.List;

public record MerchantOrder(Long orderId,
                            String status,
                            String externalReference,
                            List<Payment> payment,
                            String notificationUrl,
                            BigDecimal totalAmount) {

    public static MerchantOrder with(final Long orderId,
                                     final String status,
                                     final String externalReference,
                                     final List<Payment> payment,
                                     final String notificationUrl,
                                     final BigDecimal totalAmount) {
        return new MerchantOrder(orderId, status, externalReference, payment, notificationUrl, totalAmount);
    }
}
