package com.alura.fiap.infrastructure.gateway;


import com.alura.fiap.application.execeptions.HandlerException;
import com.alura.fiap.domain.payments.OrderQrCode;
import com.alura.fiap.domain.payments.OrderQrCodeGateway;
import com.alura.fiap.domain.payments.OrderQrCodeOut;
import com.alura.fiap.infrastructure.feign.client.MPIntegrationGateway;
import com.alura.fiap.infrastructure.models.CreateOrderQrCodeRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeCashOutRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeItemsRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class OrderQrCodeFeignGateway implements OrderQrCodeGateway {

    private static Logger logger = LoggerFactory.getLogger(OrderQrCodeFeignGateway.class);
    private final MPIntegrationGateway mpIntegrationGateway;

    public OrderQrCodeFeignGateway(final MPIntegrationGateway mpIntegrationGateway) {
        this.mpIntegrationGateway = mpIntegrationGateway;
    }

    @Override
    public OrderQrCodeOut createOrderQRCode(String authorization, OrderQrCode request, String userId, String externalPosId) {

        List<OrderQrCodeItemsRequest> orderQrCodeItemsRequests = request.items().stream()
                .map(item -> new OrderQrCodeItemsRequest(
                        item.title(),
                        item.unitMeasure(),
                        item.unitPrice(),
                        item.quantity(),
                        item.totalAmount()
                )).collect(Collectors.toList());

        var cashOut = new OrderQrCodeCashOutRequest(request.cashOut().amount());
        var createOrderQrCodeRequest = new CreateOrderQrCodeRequest(request.externalReference(), request.title(),
                orderQrCodeItemsRequests, request.totalAmount(), cashOut, request.notificationUrl(), request.description());

        final ResponseEntity<OrderQrCodeResponse> orderQRCode = mpIntegrationGateway.createOrderQRCode(authorization, createOrderQrCodeRequest, userId, externalPosId);
        logger.info("Post MP mpIntegrationGateway.createOrderQRCode: {} ", orderQRCode);
        OrderQrCodeResponse orderQRCodeResponse = orderQRCode.getBody();
        if (orderQRCodeResponse != null) {
            return new OrderQrCodeOut(Objects.requireNonNull(orderQRCode.getBody()).inStoreOrderId(), Objects.requireNonNull(orderQRCode.getBody().qrData()));
        } else {
            // Lógica para lidar com a resposta nula, se necessário
            return null;  // ou lança uma exceção, dependendo do comportamento desejado
        }

    }
}
