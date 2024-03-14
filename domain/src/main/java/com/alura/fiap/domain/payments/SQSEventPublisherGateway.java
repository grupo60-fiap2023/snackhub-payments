package com.alura.fiap.domain.payments;

public interface SQSEventPublisherGateway {

    void publishOrderStatus(OrderStatusProducer orderStatusProducer);

    void publishEventPaymentStatus(PaymentStatusProducer paymentStatusProducer);
}
