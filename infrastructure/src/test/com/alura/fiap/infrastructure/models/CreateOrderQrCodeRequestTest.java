package com.alura.fiap.infrastructure.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CreateOrderQrCodeRequestTest {

    @Test
    void createOrderQrCodeRequest_ShouldCreateObjectWithCorrectValues() {
        // Given
        String externalReference = "aWRfcGVkaWRv";
        String title = "Product Order aWRfcGVkaWRv";
        List<OrderQrCodeItemsRequest> items = List.of(
                new OrderQrCodeItemsRequest("Item1",
                        "unit", 10.0,
                        2, 20.0));
        Double totalAmount = 29.05;
        OrderQrCodeCashOutRequest cashOut = new OrderQrCodeCashOutRequest(
                5.0);
        String notificationUrl = "https://snackhubpay-mercadopago.ultrahook.com";
        String description = "Combo+Refil";

        // When
        CreateOrderQrCodeRequest orderQrCodeRequest = new CreateOrderQrCodeRequest(
                externalReference, title, items, totalAmount, cashOut, notificationUrl, description);

        // Then
        assertThat(orderQrCodeRequest.externalReference()).isEqualTo(externalReference);
        assertThat(orderQrCodeRequest.title()).isEqualTo(title);
        assertThat(orderQrCodeRequest.items()).isEqualTo(items);
        assertThat(orderQrCodeRequest.totalAmount()).isEqualTo(totalAmount);
        assertThat(orderQrCodeRequest.cashOut()).isEqualTo(cashOut);
        assertThat(orderQrCodeRequest.notificationUrl()).isEqualTo(notificationUrl);
        assertThat(orderQrCodeRequest.description()).isEqualTo(description);
    }
}
