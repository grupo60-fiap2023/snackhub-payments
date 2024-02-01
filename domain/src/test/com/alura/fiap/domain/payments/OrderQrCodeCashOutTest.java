package com.alura.fiap.domain.payments;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderQrCodeCashOutTest {

    @Test
    public void testOrderQrCodeCashOutCreation() {
        // Dado de exemplo
        Double amount = 50.0;

        // Criar uma instância da classe OrderQrCodeCashOut
        OrderQrCodeCashOut orderQrCodeCashOut = new OrderQrCodeCashOut(amount);

        // Verificar se o valor foi atribuído corretamente
        assertThat(orderQrCodeCashOut.amount()).isEqualTo(amount);
    }

    // Adicione mais testes conforme necessário, como testes para outros métodos ou cenários específicos.
}
