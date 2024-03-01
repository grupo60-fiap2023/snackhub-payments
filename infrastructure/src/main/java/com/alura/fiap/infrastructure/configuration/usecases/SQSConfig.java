package com.alura.fiap.infrastructure.configuration.usecases;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.net.URI;

@Configuration
public class SQSConfig {

    @Value("${localstack.sqs.endpoint}")
    private String localstackSqsEndpoint;

    @Value("${aws.accessKeyId}")
    private String awsAccessKey;

    @Value("${aws.secretKey}")
    private String awsSecretKey;

    @Bean
    public SqsClient sqsClient() {
        return SqsClient.builder()
                .region(Region.SA_EAST_1)
                .endpointOverride(URI.create(localstackSqsEndpoint))
                .credentialsProvider(() -> DefaultCredentialsProvider.create().resolveCredentials())
                .build();
    }

    @Value("${sqs.queue.order-status}")
    private String orderStatusQueueUrl;

    @Value("${sqs.queue.payment-status-topic}")
    private String paymentStatusTopicUrl;

    @Value("${sqs.queue.order-topic}")
    private String orderTopicQueueUrl;

    @Bean
    public String getOrderStatusQueueUrl() {
        return orderStatusQueueUrl;
    }

    @Bean
    public String getPaymentStatusTopicUrl() {
        return paymentStatusTopicUrl;
    }

    @Bean
    public String getOrderTopicQueueUrl() {
        return orderTopicQueueUrl;
    }
}
