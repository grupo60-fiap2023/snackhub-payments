package com.alura.fiap.application.create;

import com.alura.fiap.application.AuthPaymentMPUseCase;
import com.alura.fiap.application.PaymentOutput;
import com.alura.fiap.domain.payments.CreatePayment;
import com.alura.fiap.domain.payments.Identification;
import com.alura.fiap.domain.payments.Payer;
import com.alura.fiap.domain.payments.PaymentGateway;

import java.util.Objects;

public class CreatePaymentUseCase extends AuthPaymentMPUseCase<CreatePaymentCommand, PaymentOutput> {

    private final PaymentGateway paymentGateway;

    public CreatePaymentUseCase(PaymentGateway paymentGateway) {
        this.paymentGateway = Objects.requireNonNull(paymentGateway);
    }

    @Override
    public PaymentOutput execute(String authorization, CreatePaymentCommand inputObject) {

        var identification = new Identification(inputObject.payerCommand().identificationCommand().type(), inputObject.payerCommand().identificationCommand().number());
        var payer = new Payer(inputObject.payerCommand().entityType(), inputObject.payerCommand().type(), inputObject.payerCommand().email(), identification);
        var payment = new CreatePayment(inputObject.description(), inputObject.installments(), payer, inputObject.paymentMethodId(), inputObject.token(), inputObject.transactionAmount(), inputObject.issuerId());


        return PaymentOutput.from(paymentGateway.createPayment(authorization, payment));
    }
}