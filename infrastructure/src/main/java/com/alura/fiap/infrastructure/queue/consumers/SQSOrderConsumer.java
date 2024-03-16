package com.alura.fiap.infrastructure.queue.consumers;

import com.alura.fiap.infrastructure.api.OrderQrCodeAPI;
import com.alura.fiap.infrastructure.models.CreateOrderQrCodeRequest;
import com.alura.fiap.infrastructure.models.OrderConsumer;
import com.alura.fiap.infrastructure.models.OrderQrCodeCashOutRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeItemsRequest;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class SQSOrderConsumer {

    public static final String UNIT = "unit";
    public static final int QUANTITY = 1;
    public static final double AMOUNT = 0.0;
    public static final String NOTIFICATION_URL = "https://snackhubpay-mercadopago.ultrahook.com";
    private final String authorization;
    private final String userId;
    private final String externalId;
    private final OrderQrCodeAPI orderQrCodeAPI;
    private static final Logger LOGGER = LoggerFactory.getLogger(SQSOrderConsumer.class);
    private final AmazonSQSAsync amazonSQSAsync;

    public SQSOrderConsumer(@Value("${mp.authorization}") String authorization,
                            @Value("${mp.userId}") String userId,
                            @Value("${mp.externalId}") String externalId,
                            OrderQrCodeAPI orderQrCodeAPI, AmazonSQSAsync amazonSQSAsync) {
        this.authorization = authorization;
        this.userId = userId;
        this.externalId = externalId;
        this.orderQrCodeAPI = orderQrCodeAPI;
        this.amazonSQSAsync = amazonSQSAsync;
    }


    @SqsListener(value = "${cloud.aws.queue.receive-order-queue.name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receiveMessage(Message message) {
            LOGGER.info("Read Message from queue: {}", message.getBody());
        try {
            Gson gson = new GsonBuilder().create();
            OrderConsumer orderConsumer = gson.fromJson(String.valueOf(message), OrderConsumer.class);

            CreateOrderQrCodeRequest createOrderQrCodeRequest = new CreateOrderQrCodeRequest(
                    orderConsumer.getOrderId().toString(),
                    "Order ID: " + orderConsumer.getOrderId().toString() +
                            " Customer Id: " + orderConsumer.getOrderIdentifier() +
                            " Order Identifier: " + orderConsumer.getOrderIdentifier(),
                    Collections.singletonList(
                            new OrderQrCodeItemsRequest(
                                    orderConsumer.getCustomerId().toString(),
                                    UNIT,
                                    orderConsumer.getTotalAmount(),
                                    QUANTITY,
                                    orderConsumer.getTotalAmount(),
                                    orderConsumer.getOrderIdentifier()
                            )
                    ),
                    orderConsumer.getTotalAmount(),
                    new OrderQrCodeCashOutRequest(AMOUNT),
                    NOTIFICATION_URL,
                    "Order ID: " + orderConsumer.getOrderId().toString() +
                            " Customer Id: " + orderConsumer.getOrderIdentifier() +
                            " Order Identifier: " + orderConsumer.getOrderIdentifier());
            orderQrCodeAPI.createOrderQrCode(authorization, createOrderQrCodeRequest, userId, externalId);
            amazonSQSAsync.deleteMessage(String.valueOf(message), message.getReceiptHandle());
        } catch (NumberFormatException | NullPointerException e) {
            LOGGER.error("Error processing SQS message: {}", e.getMessage(), e);
        }
    }
}