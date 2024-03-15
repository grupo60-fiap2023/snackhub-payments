package com.alura.fiap.infrastructure.persistence;

import com.alura.fiap.domain.payments.MerchantOrder;
import com.alura.fiap.infrastructure.gateway.MerchantOrderMongoDBGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@AutoConfigureDataMongo
@SpringBootTest
class MerchantOrderMongoDBGatewayIntegrationTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MerchantOrderMongoDBGateway merchantOrderMongoDBGateway;

    @BeforeEach
    public void setUp() {
        mongoTemplate.getDb().drop();
    }

    @Test
    @DisplayName("Save and Find Merchant Order Payment by External Reference")
    void testSaveAndFindMerchantOrderPaymentByExternalReference() {
        // Dados de exemplo
        MerchantOrder merchantOrder = new MerchantOrder(
                1L,
                "PAID",
                "externalReference",
                "title",
                "description",
                List.of(),
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
        assertEquals(merchantOrder.title(), foundOrders.get(0).title());
        assertEquals(merchantOrder.description(), foundOrders.get(0).description());
        assertEquals(merchantOrder.notificationUrl(), foundOrders.get(0).notificationUrl());
        assertEquals(merchantOrder.totalAmount(), foundOrders.get(0).totalAmount());
    }
}
