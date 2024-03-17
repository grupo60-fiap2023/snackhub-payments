package com.alura.fiap.infrastructure.queue.producers;

import com.alura.fiap.domain.payments.OrderStatusProducer;
import com.alura.fiap.domain.payments.PaymentStatusProducer;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SQSEventPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(SQSEventPublisher.class);

    private final AmazonSQS amazonSQS;

    private final ObjectMapper objectMapper;


    public SQSEventPublisher(AmazonSQS amazonSQS, ObjectMapper objectMapper) {
        this.amazonSQS = amazonSQS;
        this.objectMapper = objectMapper;
    }

    @Value("${cloud.aws.queue.order-status-queue.url}")
    private String orderStatusQueueUrl;

    @Value("${cloud.aws.queue.payment-status-queue.url}")
    private String paymentQueueUrl;

    public void publishEventOrderStatus(OrderStatusProducer message) {
        try {
            String messageBody = objectMapper.writeValueAsString(message);

            SendMessageRequest sendMessageRequest = new SendMessageRequest()
                    .withQueueUrl(orderStatusQueueUrl)
                    .withMessageBody(messageBody);

            amazonSQS.sendMessage(sendMessageRequest);
            LOGGER.info("Event OrderStatus has been published in SQS - {} ",message);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while processing OrderStatus JSON: {}", e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error("Exception occurred while pushing event OrderStatus to SQS: {}", e.getMessage(), e);
        }
    }

    public void publishEventPaymentStatus(PaymentStatusProducer message) {
        try {
            String messageBody = objectMapper.writeValueAsString(message);

            SendMessageRequest sendMessageRequest = new SendMessageRequest()
                    .withQueueUrl(paymentQueueUrl)
                    .withMessageBody(messageBody);

            amazonSQS.sendMessage(sendMessageRequest);
            LOGGER.info("Event PaymentStatus has been published in SQS {}", message);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while processing PaymentStatus JSON: {}", e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error("Exception occurred while pushing event PaymentStatus to SQS: {}", e.getMessage(), e);
        }
    }
}