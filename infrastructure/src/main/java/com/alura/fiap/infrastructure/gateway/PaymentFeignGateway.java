package com.alura.fiap.infrastructure.gateway;

import com.alura.fiap.domain.payments.CreatePayment;
import com.alura.fiap.domain.payments.PaymentGateway;
import com.alura.fiap.infrastructure.feign.client.MPIntegrationGateway;
import com.alura.fiap.infrastructure.models.PayerRequest;
import com.alura.fiap.infrastructure.models.PaymentCreateRequest;
import com.alura.fiap.infrastructure.models.PaymentIdentificationRequest;
import com.mercadopago.resources.payment.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFeignGateway implements PaymentGateway {

    private final MPIntegrationGateway mpIntegrationGateway;

    public PaymentFeignGateway(final MPIntegrationGateway mpIntegrationGateway) {
        this.mpIntegrationGateway = mpIntegrationGateway;
    }

    @Override
    public Payment createPayment(String authorization, CreatePayment request) {

        var identification = new PaymentIdentificationRequest(request.getPayer().identification().type(), request.getPayer().identification().number());
        var payer = new PayerRequest(request.getPayer().entityType(), request.getPayer().type(), request.getPayer().email(), identification);
        var createPaymentRequest = new PaymentCreateRequest(request.getDescription(), request.getInstallments(),
                payer, request.getPaymentMethodId(), request.getToken(), request.getTransactionAmount(), request.getIssuerId());

        return mpIntegrationGateway.createPayment(authorization, createPaymentRequest).getBody();
    }
}
