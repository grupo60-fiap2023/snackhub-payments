package com.alura.fiap.infrastructure.gateway;


import com.alura.fiap.domain.payments.*;
import com.alura.fiap.infrastructure.feign.client.MPIntegrationGateway;
import com.alura.fiap.infrastructure.models.CreateOrderQrCodeRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeCashOutRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeItemsRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeResponse;
import com.alura.fiap.infrastructure.queue.producers.SQSEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class OrderQrCodeFeignGateway implements OrderQrCodeGateway {

    public static final String PENDING_PAYMENT = "pending_payment";
    private static Logger logger = LoggerFactory.getLogger(OrderQrCodeFeignGateway.class);
    private final MPIntegrationGateway mpIntegrationGateway;

    private final SQSEventPublisher sqsEventPublisher;

    private final MerchantOrderPaymentGateway merchantOrderPaymentGateway;

    public OrderQrCodeFeignGateway(final MPIntegrationGateway mpIntegrationGateway, SQSEventPublisher sqsEventPublisher, MerchantOrderPaymentGateway merchantOrderPaymentGateway) {
        this.mpIntegrationGateway = mpIntegrationGateway;
        this.sqsEventPublisher = sqsEventPublisher;
        this.merchantOrderPaymentGateway = merchantOrderPaymentGateway;
    }

    @Override
    public OrderQrCodeOut createOrderQRCode(String authorization, OrderQrCode request, String userId, String externalPosId) {

        List<OrderQrCodeItemsRequest> orderQrCodeItemsRequests = request.items().stream()
                .map(item -> new OrderQrCodeItemsRequest(item.title(), item.unitMeasure(), item.unitPrice(), item.quantity(),
                        item.totalAmount(), item.description()
                )).toList();

        var cashOut = new OrderQrCodeCashOutRequest(request.cashOut().amount());
        var createOrderQrCodeRequest = new CreateOrderQrCodeRequest(request.externalReference(), request.title(),
                orderQrCodeItemsRequests, request.totalAmount(), cashOut, request.notificationUrl(), request.description());

        final ResponseEntity<OrderQrCodeResponse> orderQRCode =
                mpIntegrationGateway.createOrderQRCode(authorization,
                        createOrderQrCodeRequest, userId, externalPosId);
        logger.info("Post MP mpIntegrationGateway.createOrderQRCode: {} ", orderQRCode.getBody());

        OrderQrData orderQrData = OrderQrData.with(request.externalReference(), request.items().get(0).title(), request.items().get(0).description(),
                Objects.requireNonNull(orderQRCode.getBody()).inStoreOrderId(), Objects.requireNonNull(orderQRCode.getBody().qrData()));
        merchantOrderPaymentGateway.saveOrderConsumer(orderQrData);
        logger.info("Save in Data Base: {} ", orderQrData);

        OrderStatusProducer orderStatusProducer = OrderStatusProducer.with(request.externalReference(), PENDING_PAYMENT);
        sqsEventPublisher.publishEventOrderStatus(orderStatusProducer);

        return new OrderQrCodeOut(Objects.requireNonNull(orderQRCode.getBody()).inStoreOrderId(), Objects.requireNonNull(orderQRCode.getBody().qrData()));
    }
}
