package com.alura.fiap.infrastructure.gateway;



import com.alura.fiap.domain.payments.OrderQrCode;
import com.alura.fiap.domain.payments.OrderQrCodeGateway;
import com.alura.fiap.domain.payments.OrderQrCodeOut;
import com.alura.fiap.infrastructure.feign.client.MPIntegrationGateway;
import com.alura.fiap.infrastructure.models.CreateOrderQrCodeRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeCashOutRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeItemsRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class OrderQrCodeFeignGateway implements OrderQrCodeGateway {

    private final MPIntegrationGateway mpIntegrationGateway;

    public OrderQrCodeFeignGateway(final MPIntegrationGateway mpIntegrationGateway) {
        this.mpIntegrationGateway = mpIntegrationGateway;
    }

    @Override
    public OrderQrCodeOut createOrderQRCode(String authorization, OrderQrCode request, String userId, String externalPosId) {
        List<OrderQrCodeItemsRequest> orderQrCodeItemsRequests = List.of(new OrderQrCodeItemsRequest(
                request.getItems().get(0).id(), request.getItems().get(0).skuNumber(), request.getItems().get(0).category(),
                request.getItems().get(0).title(), request.getItems().get(0).description(), request.getItems().get(0).unitPrice(),
                request.getItems().get(0).quantity(), request.getItems().get(0).unitMeasure(), request.getItems().get(0).totalAmount()));

        var cashOut = new OrderQrCodeCashOutRequest(request.getCashOut().amount());
        var createOrderQrCodeRequest = new CreateOrderQrCodeRequest(request.getExternalReference(), request.getTitle(),
                request.getNotificationUrl(), request.getTotalAmount(), orderQrCodeItemsRequests, cashOut, request.getDescription());

        final ResponseEntity<OrderQrCodeResponse> orderQRCode = mpIntegrationGateway.createOrderQRCode(authorization, createOrderQrCodeRequest, userId, externalPosId);

        return new OrderQrCodeOut(Objects.requireNonNull(orderQRCode.getBody()).inStoreOrderId(), Objects.requireNonNull(orderQRCode.getBody().qrData()));

    }
}
