package com.alura.fiap.infrastructure.gateway;


import com.alura.fiap.domain.payments.*;
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

@Component
public class OrderQrCodeFeignGateway implements OrderQrCodeGateway {
    private static final Logger logger = LoggerFactory.getLogger(OrderQrCodeFeignGateway.class);
    private final MPIntegrationGateway mpIntegrationGateway;

    private final MerchantOrderPaymentGateway merchantOrderPaymentGateway;

    public OrderQrCodeFeignGateway(final MPIntegrationGateway mpIntegrationGateway, MerchantOrderPaymentGateway merchantOrderPaymentGateway) {
        this.mpIntegrationGateway = mpIntegrationGateway;
        this.merchantOrderPaymentGateway = merchantOrderPaymentGateway;
    }

    @Override
    public OrderQrCodeOut createOrderQRCode(String authorization, OrderQrCode request, String userId, String externalPosId) {
        // Validate input parameters
        if (authorization == null || request == null || userId == null || externalPosId == null) {
            throw new IllegalArgumentException("Null parameter(s) detected");
        }

        try {
            // Map order items to OrderQrCodeItemsRequest
            List<OrderQrCodeItemsRequest> orderQrCodeItemsRequests = request.items().stream()
                    .map(item -> new OrderQrCodeItemsRequest(
                            item.title(),
                            item.unitMeasure(),
                            item.unitPrice(),
                            item.quantity(),
                            item.totalAmount(),
                            item.description()
                    ))
                    .toList();

            // Create order QR code request
            OrderQrCodeCashOutRequest cashOut = new OrderQrCodeCashOutRequest(request.cashOut().amount());
            CreateOrderQrCodeRequest createOrderQrCodeRequest = new CreateOrderQrCodeRequest(
                    request.externalReference(),
                    request.title(),
                    orderQrCodeItemsRequests,
                    request.totalAmount(),
                    cashOut,
                    request.notificationUrl(),
                    request.description()
            );

            // Call integration gateway to create order QR code
            ResponseEntity<OrderQrCodeResponse> orderQRCodeResponse = mpIntegrationGateway.createOrderQRCode(authorization, createOrderQrCodeRequest, userId, externalPosId);

            // Log response from integration gateway
            if (orderQRCodeResponse != null && orderQRCodeResponse.getBody() != null) {
                logger.info("Order QR code creation response: {}", orderQRCodeResponse.getBody());
            } else {
                logger.warn("Order QR code creation response is null");
            }

            // Process response and save order data
            if (orderQRCodeResponse != null && orderQRCodeResponse.getBody() != null) {
                OrderQrCodeResponse responseBody = orderQRCodeResponse.getBody();
                OrderQrData orderQrData = OrderQrData.with(
                        request.externalReference(),
                        request.items().get(0).title(),
                        request.items().get(0).description(),
                        responseBody.inStoreOrderId(),
                        responseBody.qrData()
                );

                // Save order data
                merchantOrderPaymentGateway.saveOrderConsumer(orderQrData);
                logger.info("Order data saved: {}", orderQrData);

                // Return OrderQrCodeOut object
                return new OrderQrCodeOut(responseBody.inStoreOrderId(), responseBody.qrData());
            } else {
                throw new IllegalStateException("Failed to create order QR code");
            }
        } catch (Exception e) {
            // Handle other exceptions
            logger.error("Failed to create order QR code: {}", e.getMessage());
            throw new IllegalStateException("Failed to create order QR code");
        }

        }
    }
