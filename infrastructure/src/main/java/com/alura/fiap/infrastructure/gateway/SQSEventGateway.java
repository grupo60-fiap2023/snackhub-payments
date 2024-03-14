package com.alura.fiap.infrastructure.gateway;


import com.alura.fiap.domain.payments.OrderStatusProducer;
import com.alura.fiap.domain.payments.PaymentStatusProducer;
import com.alura.fiap.domain.payments.SQSEventPublisherGateway;
import com.alura.fiap.infrastructure.producers.SQSEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SQSEventGateway implements SQSEventPublisherGateway {

    private final SQSEventPublisher sqsEventPublisher;

    public SQSEventGateway(SQSEventPublisher sqsEventPublisher) {
        this.sqsEventPublisher = sqsEventPublisher;
    }

    @Override
    public void publishOrderStatus(OrderStatusProducer orderStatusProducer) {
        this.sqsEventPublisher.publishEventOrderStatus(orderStatusProducer);
    }

    @Override
    public void publishEventPaymentStatus(PaymentStatusProducer paymentStatusProducer) {
        this.sqsEventPublisher.publishEventPaymentStatus(paymentStatusProducer);
    }

}