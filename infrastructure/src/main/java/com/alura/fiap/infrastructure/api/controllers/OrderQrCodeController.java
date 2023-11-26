package com.alura.fiap.infrastructure.api.controllers;

import com.alura.fiap.application.create.CreateOrderQrCodeCommand;
import com.alura.fiap.application.create.CreateOrderQrCodeUseCase;
import com.alura.fiap.infrastructure.api.OrderQrCodeAPI;
import com.alura.fiap.infrastructure.models.CreateOrderQrCodeRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeResponse;
import com.alura.fiap.infrastructure.presenters.OrderQrCodeApiPresenter;
import com.alura.fiap.infrastructure.presenters.OrderQrCodeCashOutApiPresenter;
import com.alura.fiap.infrastructure.presenters.OrderQrCodeItemApiPresenter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderQrCodeController implements OrderQrCodeAPI {

    private final CreateOrderQrCodeUseCase createOrderQrCodeUseCase;


    public OrderQrCodeController(final CreateOrderQrCodeUseCase createOrderQrCodeUseCase) {
        this.createOrderQrCodeUseCase = createOrderQrCodeUseCase;
    }

    @Override
    public ResponseEntity<OrderQrCodeResponse> createOrderQrCode(String authorization, CreateOrderQrCodeRequest request, String userId, String externalPosId) {
        OrderQrCodeResponse response;

        var items = OrderQrCodeItemApiPresenter.present(request.items());
        var cashOut = OrderQrCodeCashOutApiPresenter.present(request.cashOut());

        var command = CreateOrderQrCodeCommand.with(request.externalReference(),
                request.title(),
                request.notificationUrl(),
                request.totalAmount(),
                items,
                cashOut,
                request.description());

        var orderQrCode = this.createOrderQrCodeUseCase.execute(authorization, command, userId, externalPosId);
        response = OrderQrCodeApiPresenter.present(orderQrCode);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
