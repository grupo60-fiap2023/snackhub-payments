package com.alura.fiap.infrastructure.api.controllers;


import com.alura.fiap.application.PaymentOutput;
import com.alura.fiap.application.create.CreatePaymentCommand;
import com.alura.fiap.application.create.CreatePaymentUseCase;
import com.alura.fiap.application.create.IdentificationCommand;
import com.alura.fiap.application.create.PayerCommand;
import com.alura.fiap.infrastructure.api.PaymentAPI;
import com.alura.fiap.infrastructure.models.PaymentCreateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentAPIController implements PaymentAPI {

    private final CreatePaymentUseCase createPaymentUseCase;

    public PaymentAPIController(final CreatePaymentUseCase createPaymentUseCase) {
        this.createPaymentUseCase = createPaymentUseCase;
    }


    @Override
    public ResponseEntity<PaymentOutput> createPayment(String authorization, PaymentCreateRequest request) {

        var identification = new IdentificationCommand(request.payerRequest().identification().type(), request.payerRequest().identification().number());
        var payer = new PayerCommand(request.payerRequest().entityType(), request.payerRequest().type(),
                request.payerRequest().email(), identification);
        var in = new CreatePaymentCommand(request.description(), request.installments(), payer, request.paymentMethodId(), request.token(), request.transactionAmount(), request.issuerId());

        PaymentOutput execute = createPaymentUseCase.execute(authorization, in);

        return ResponseEntity.status(HttpStatus.CREATED).body(execute);
    }
}
