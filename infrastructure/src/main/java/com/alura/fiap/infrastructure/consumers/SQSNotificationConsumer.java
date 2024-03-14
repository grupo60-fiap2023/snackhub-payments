package com.alura.fiap.infrastructure.consumers;

import com.alura.fiap.infrastructure.models.NotificationConsumer;
import com.google.gson.Gson;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SQSNotificationConsumer {

 /*   private final SqsClient sqsClient;

    public SQSNotificationConsumer(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    @Scheduled(fixedDelay = 10000) // Ajuste o intervalo conforme necessário
    public void pollQueue() {
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl("YOUR_SQS_QUEUE_URL") // Substitua pela URL da sua fila SQS
                .maxNumberOfMessages(10)
                .build();

        List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).messages();
        for (Message message : messages) {
            String notification = message.body();
            // Processar a notificação recebida da fila SQS
            System.out.println("Notification received from SQS: " + notification);
        }
    }*/

    private static final Logger LOGGER = LoggerFactory.getLogger(SQSNotificationConsumer.class);


    @SqsListener("${cloud.aws.queue.notification-queue.name}")
    public void receiveNotificationMP(String message) {
        LOGGER.info("SQS Message Received Notification MP: {}", message);
        NotificationConsumer notificationConsumer = new Gson().fromJson(message, NotificationConsumer.class);
        try {
            LOGGER.info("SQS Message Received Notification MP: {}", notificationConsumer);

        } catch (NumberFormatException | NullPointerException e) {
            LOGGER.error("Error processing SQS message: {}", e.getMessage(), e);
        }

    }
}