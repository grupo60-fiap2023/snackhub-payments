package com.alura.fiap.infrastructure.persistence;

import com.alura.fiap.domain.payments.MerchantOrder;
import com.alura.fiap.infrastructure.gateway.MerchantOrderMongoDBGateway;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class MerchantOrderMongoDBGatewayIntegrationTest {

    @Autowired
    private MerchantOrderMongoDBGateway merchantOrderMongoDBGateway;


    public void testSaveAndFindMerchantOrderPaymentByExternalReference() {
        // Dados de exemplo
        MerchantOrder merchantOrder = new MerchantOrder(
                1L,
                "PAID",
                "externalReference",
                null,
                "notificationUrl",
                BigDecimal.valueOf(100.0)
        );

        // Salvar no MongoDB em memória
        merchantOrderMongoDBGateway.saveMerchantOrderPayment(merchantOrder);

        // Buscar do MongoDB em memória
        List<MerchantOrder> foundOrders = merchantOrderMongoDBGateway.findMerchantOrderPaymentByExternalReference("externalReference");

        // Verificar se o resultado é o esperado
        assertEquals(1, foundOrders.size());
        assertEquals(merchantOrder.orderId(), foundOrders.get(0).orderId());
        assertEquals(merchantOrder.status(), foundOrders.get(0).status());
        assertEquals(merchantOrder.externalReference(), foundOrders.get(0).externalReference());
        assertEquals(merchantOrder.notificationUrl(), foundOrders.get(0).notificationUrl());
        assertEquals(merchantOrder.totalAmount(), foundOrders.get(0).totalAmount());
    }
}