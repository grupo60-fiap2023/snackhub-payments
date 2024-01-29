package com.alura.fiap.infrastructure.persistence;

import com.alura.fiap.domain.payments.MerchantOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DocumentMerchantOrderTest {
    @Mock
    MerchantOrder merchantOrder;


    public void testCreateFromMerchantOrder() {
        // Dados de exemplo
        Long orderId = 1L;
        String status = "pending";
        String externalReference = "extRef123";
        String notificationUrl = "http://example.com/notification";
        BigDecimal totalAmount = new BigDecimal("100.00");


        when(merchantOrder.orderId()).thenReturn(orderId);
        when(merchantOrder.status()).thenReturn(status);
        when(merchantOrder.externalReference()).thenReturn(externalReference);
        when(merchantOrder.notificationUrl()).thenReturn(notificationUrl);
        when(merchantOrder.totalAmount()).thenReturn(totalAmount);

        // Criação de um DocumentMerchantOrder a partir do MerchantOrder
        DocumentMerchantOrder documentMerchantOrder = DocumentMerchantOrder.create(merchantOrder);

        // Verificação dos valores
        Assertions.assertEquals(orderId, documentMerchantOrder.getOrderId());
        Assertions.assertEquals(status, documentMerchantOrder.getStatus());
        Assertions.assertEquals(externalReference, documentMerchantOrder.getExternalReference());
        Assertions.assertEquals(notificationUrl, documentMerchantOrder.getNotificationUrl());
        Assertions.assertEquals(totalAmount, documentMerchantOrder.getTotalAmount());
        Assertions.assertEquals(Collections.emptyList(), documentMerchantOrder.getDocumentPayments());
    }

    public void testWithTotalAmount() {
        // Criação de um DocumentMerchantOrder de exemplo
        DocumentMerchantOrder originalDocumentMerchantOrder = new DocumentMerchantOrder(
                1L,
                "pending",
                "extRef123",
                Collections.emptyList(),
                "http://example.com/notification",
                new BigDecimal("100.00")
        );

        // Valor total atualizado
        BigDecimal newTotalAmount = new BigDecimal("150.00");

        // Criação de uma nova instância com o totalAmount atualizado
        DocumentMerchantOrder updatedDocumentMerchantOrder = originalDocumentMerchantOrder.withTotalAmount(newTotalAmount);

        // Verificação dos valores
        Assertions.assertEquals(originalDocumentMerchantOrder.getOrderId(), updatedDocumentMerchantOrder.getOrderId());
        Assertions.assertEquals(originalDocumentMerchantOrder.getStatus(), updatedDocumentMerchantOrder.getStatus());
        Assertions.assertEquals(originalDocumentMerchantOrder.getExternalReference(), updatedDocumentMerchantOrder.getExternalReference());
        Assertions.assertEquals(originalDocumentMerchantOrder.getNotificationUrl(), updatedDocumentMerchantOrder.getNotificationUrl());
        Assertions.assertEquals(newTotalAmount, updatedDocumentMerchantOrder.getTotalAmount());
        Assertions.assertEquals(originalDocumentMerchantOrder.getDocumentPayments(), updatedDocumentMerchantOrder.getDocumentPayments());
    }
}
