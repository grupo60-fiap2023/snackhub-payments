package com.alura.fiap.domain.payments;

import java.math.BigDecimal;
import java.util.List;

public record MerchantOrder(Long orderId,
                            String status,
                            String externalReference,
                            String title,
                            String description,
                            List<Payment> payment,
                            String notificationUrl,
                            BigDecimal totalAmount) {

    public static MerchantOrder with(final Long orderId,
                                     final String status,
                                     final String externalReference,
                                     final String title,
                                     final String description,
                                     final List<Payment> payment,
                                     final String notificationUrl,
                                     final BigDecimal totalAmount) {
        return new MerchantOrder(orderId, status, externalReference, title, description, payment, notificationUrl, totalAmount);
    }
}
