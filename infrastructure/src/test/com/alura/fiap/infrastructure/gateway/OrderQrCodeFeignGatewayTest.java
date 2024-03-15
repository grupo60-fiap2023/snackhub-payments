package com.alura.fiap.infrastructure.gateway;

import com.alura.fiap.domain.payments.*;
import com.alura.fiap.infrastructure.feign.client.MPIntegrationGateway;
import com.alura.fiap.infrastructure.models.CreateOrderQrCodeRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeCashOutRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeItemsRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeResponse;
import com.alura.fiap.infrastructure.queue.producers.SQSEventPublisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderQrCodeFeignGatewayTest {

    @InjectMocks
    OrderQrCodeFeignGateway orderQrCodeFeignGateway;

    @Mock
    MPIntegrationGateway mpIntegrationGateway;

    @Mock
    SQSEventPublisher sqsEventPublisher;

    @Mock
    MerchantOrderPaymentGateway merchantOrderPaymentGateway;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateOrderQRCode() {
        // Configuração do mock de resposta
        String authorization = "testAuthorization";
        String userId = "testUserId";
        String externalPosId = "testExternalPosId";

        OrderQrCode request = new OrderQrCode(
                "orderId",
                "title",
                "notificationUrl",
                29.05,
                Collections.singletonList(new OrderQrCodeItem(
                        "customerId",
                        "unitMeasure"
                        , 10.0, 2,
                        20.0,
                        "IdentifierId")),

                new OrderQrCodeCashOut(0.0),
                "description"
        );

        OrderQrCodeResponse mockOrderQrCodeResponse = new OrderQrCodeResponse("inStoreOrderId", "qrData");


        OrderQrData mockOrderQrData = new OrderQrData(
                "orderId",
                "customerId",
                "IdentifierId",
                "inStoreOrderId",
                "qrData"
        );
        // Configurar o comportamento do mock
        doNothing().when(merchantOrderPaymentGateway).saveOrderConsumer(mockOrderQrData);

        when(mpIntegrationGateway.createOrderQRCode(anyString(), any(CreateOrderQrCodeRequest.class), anyString(), anyString())).thenReturn(ResponseEntity.ok(mockOrderQrCodeResponse));

        // Chamada do método a ser testado
        OrderQrCodeOut orderQRCode = orderQrCodeFeignGateway.createOrderQRCode(authorization, request, userId, externalPosId);

        // Verificações
        assertThat(orderQRCode).isNotNull();  // Verifica se o resultado não é nulo
        assertThat(orderQRCode.inStoreOrderId()).isEqualTo("inStoreOrderId");
        assertThat(orderQRCode.qrData()).isEqualTo("qrData");
        verify(mpIntegrationGateway, Mockito.times(1)).createOrderQRCode(anyString(), any(CreateOrderQrCodeRequest.class), anyString(), anyString());

        System.out.println("In Store Order ID: Expected: " + mockOrderQrCodeResponse.inStoreOrderId() + ", Actual: " + "inStoreOrderId");
        System.out.println("QR Data: Expected: " + mockOrderQrCodeResponse.qrData() + ", Actual: " + "qrData");

        // Adicione asserts adicionais
        assertThat(mockOrderQrCodeResponse).isNotNull();  // Verifica se o resultado não é nulo
        assertThat(mockOrderQrCodeResponse.inStoreOrderId()).isEqualTo("inStoreOrderId");
        assertThat(mockOrderQrCodeResponse.qrData()).isEqualTo("qrData");

        // Adicione verificações de chamada adicionais
        verify(mpIntegrationGateway, times(1)).createOrderQRCode(anyString(), any(CreateOrderQrCodeRequest.class), anyString(), anyString());
    }


    private static CreateOrderQrCodeRequest getCreateOrderQrCodeRequest() {
        OrderQrCodeItemsRequest expectedItemRequest = new OrderQrCodeItemsRequest(
                "itemTitle",
                "unitMeasure",
                10.0,
                2,
                20.0,
                "description"
        );

        CreateOrderQrCodeRequest expectedCreateOrderRequest = new CreateOrderQrCodeRequest(
                "externalReference",
                "title",
                Collections.singletonList(expectedItemRequest),
                29.05,
                new OrderQrCodeCashOutRequest(0.0),
                "notificationUrl",
                "description"
        );
        return expectedCreateOrderRequest;
    }
}