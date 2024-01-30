package com.alura.fiap.domain.payments;

import org.junit.experimental.categories.Categories;

import java.util.List;

@Categories.ExcludeCategory
public record OrderQrCode(String externalReference, String title, String notificationUrl, Double totalAmount,
                          List<OrderQrCodeItem> items, OrderQrCodeCashOut cashOut, String description) {

}