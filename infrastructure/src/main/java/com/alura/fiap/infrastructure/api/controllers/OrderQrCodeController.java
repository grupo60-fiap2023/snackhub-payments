package com.alura.fiap.infrastructure.api.controllers;

import com.alura.fiap.application.create.CreateImageQrCodeCommand;
import com.alura.fiap.application.create.CreateImageQrCodeUseCase;
import com.alura.fiap.application.create.CreateOrderQrCodeCommand;
import com.alura.fiap.application.create.CreateOrderQrCodeUseCase;
import com.alura.fiap.infrastructure.api.OrderQrCodeAPI;
import com.alura.fiap.infrastructure.models.CreateOrderQrCodeRequest;
import com.alura.fiap.infrastructure.presenters.OrderQrCodeApiPresenter;
import com.alura.fiap.infrastructure.presenters.OrderQrCodeCashOutApiPresenter;
import com.alura.fiap.infrastructure.presenters.OrderQrCodeItemApiPresenter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderQrCodeController implements OrderQrCodeAPI {

    private final CreateOrderQrCodeUseCase createOrderQrCodeUseCase;
    private final CreateImageQrCodeUseCase createImageQrCodeUseCase;


    public OrderQrCodeController(final CreateOrderQrCodeUseCase createOrderQrCodeUseCase, final CreateImageQrCodeUseCase createImageQrCodeUseCase) {
        this.createOrderQrCodeUseCase = createOrderQrCodeUseCase;
        this.createImageQrCodeUseCase = createImageQrCodeUseCase;
    }

    @Override
    public ResponseEntity<byte[]> createMerchantOrderQrCode(String authorization, String accessToken, CreateOrderQrCodeRequest request,
                                                            String userId, String externalPosId) {

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

        OrderQrCodeApiPresenter.present(orderQrCode);

        final byte[] image = createImageQrCodeUseCase.execute(CreateImageQrCodeCommand.with(orderQrCode.qrData()));

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
    }
}
