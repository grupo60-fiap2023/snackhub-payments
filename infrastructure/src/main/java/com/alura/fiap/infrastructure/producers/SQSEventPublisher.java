package com.alura.fiap.infrastructure.producers;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SQSEventPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(SQSEventPublisher.class);

    @Autowired
    private AmazonSQS amazonSQS;

    @Autowired
    private ObjectMapper objectMapper;

    public void publishEvent(JsonNode message) {
        try {
            String messageBody = objectMapper.writeValueAsString(message);

            SendMessageRequest sendMessageRequest = new SendMessageRequest()
                    .withQueueUrl("http://localhost:4566/000000000000/order-status")
                    .withMessageBody(messageBody);

            amazonSQS.sendMessage(sendMessageRequest);
            LOGGER.info("Event has been published in SQS.");
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while processing JSON: {}", e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error("Exception occurred while pushing event to SQS: {}", e.getMessage(), e);
        }
    }
}