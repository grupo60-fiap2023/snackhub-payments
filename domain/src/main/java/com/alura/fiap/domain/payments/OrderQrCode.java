package com.alura.fiap.domain.payments;

import java.util.List;

public record OrderQrCode(String externalReference, String title, String notificationUrl, Double totalAmount,
                          List<OrderQrCodeItem> items, OrderQrCodeCashOut cashOut, String description) {

}