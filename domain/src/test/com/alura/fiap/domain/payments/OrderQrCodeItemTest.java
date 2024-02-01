package com.alura.fiap.domain.payments;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderQrCodeItemTest {

    @Test
    public void testOrderQrCodeItemCreation() {
        // Dados de exemplo
        String title = "Test Item";
        String unitMeasure = "Test Unit";
        Double unitPrice = 10.0;
        Integer quantity = 2;
        Double totalAmount = 20.0;

        OrderQrCodeItem orderQrCodeItem = OrderQrCodeItem.with(title, unitMeasure, unitPrice, quantity, totalAmount);

        assertThat(orderQrCodeItem.title()).isEqualTo(title);
        assertThat(orderQrCodeItem.unitMeasure()).isEqualTo(unitMeasure);
        assertThat(orderQrCodeItem.unitPrice()).isEqualTo(unitPrice);
        assertThat(orderQrCodeItem.quantity()).isEqualTo(quantity);
        assertThat(orderQrCodeItem.totalAmount()).isEqualTo(totalAmount);
    }

    // Adicione mais testes conforme necessário, como testes para outros métodos ou cenários específicos.
}
