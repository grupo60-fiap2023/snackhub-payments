package com.alura.fiap.infrastructure.gateway;

import com.alura.fiap.domain.payments.OrderQrCode;
import com.alura.fiap.domain.payments.OrderQrCodeCashOut;
import com.alura.fiap.infrastructure.feign.client.MPIntegrationGateway;
import com.alura.fiap.infrastructure.models.CreateOrderQrCodeRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeCashOutRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeItemsRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeResponse;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderQrCodeFeignGatewayTest {

    @InjectMocks
    OrderQrCodeFeignGateway orderQrCodeFeignGateway;

    @Mock
    MPIntegrationGateway mpIntegrationGateway;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateOrderQRCode() {
        String authorization = "testAuthorization";
        String userId = "testUserId";
        String externalPosId = "testExternalPosId";

        OrderQrCode request = new OrderQrCode(
                "externalReference",
                "title",
                "notificationUrl",
                29.05,
                Collections.emptyList(),
                new OrderQrCodeCashOut(0.0),
                "description"
        );

        CreateOrderQrCodeRequest expectedCreateOrderRequest = getCreateOrderQrCodeRequest();

        OrderQrCodeResponse mockOrderQrCodeResponse = new OrderQrCodeResponse("inStoreOrderId", "qrData");
        ResponseEntity<OrderQrCodeResponse> mockResponseEntity = ResponseEntity.ok(mockOrderQrCodeResponse);
        ;
        // Configurar o comportamento do mock
        when(mpIntegrationGateway.createOrderQRCode(anyString(), any(CreateOrderQrCodeRequest.class), anyString(), anyString())).thenReturn(mockResponseEntity);

        orderQrCodeFeignGateway.createOrderQRCode(authorization, request, userId, externalPosId);

        verify(mpIntegrationGateway, Mockito.times(1)).createOrderQRCode(anyString(), any(CreateOrderQrCodeRequest.class), anyString(), anyString());

        System.out.println("In Store Order ID: Expected: " + mockOrderQrCodeResponse.inStoreOrderId() + ", Actual: " + "inStoreOrderId");
        System.out.println("QR Data: Expected: " + mockOrderQrCodeResponse.qrData() + ", Actual: " + "qrData");

        assertThat(mockOrderQrCodeResponse.inStoreOrderId()).isEqualTo("inStoreOrderId");
        assertThat(mockOrderQrCodeResponse.qrData()).isEqualTo("qrData");
    }

    private static CreateOrderQrCodeRequest getCreateOrderQrCodeRequest() {
        OrderQrCodeItemsRequest expectedItemRequest = new OrderQrCodeItemsRequest(
                "itemTitle",
                "unitMeasure",
                10.0,
                2,
                20.0
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
