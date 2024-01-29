package com.alura.fiap.infrastructure.api.controllers;

import com.alura.fiap.application.create.CreateImageQrCodeCommand;
import com.alura.fiap.application.create.CreateImageQrCodeUseCase;
import com.alura.fiap.application.create.CreateOrderQrCodeCommand;
import com.alura.fiap.application.create.CreateOrderQrCodeUseCase;
import com.alura.fiap.infrastructure.api.OrderQrCodeAPI;
import com.alura.fiap.infrastructure.exception.CustomOrderQrCodeException;
import com.alura.fiap.infrastructure.models.CreateOrderQrCodeRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeResponse;
import com.alura.fiap.infrastructure.models.OrderQrCodeResponseWrapper;
import com.alura.fiap.infrastructure.presenters.OrderQrCodeCashOutApiPresenter;
import com.alura.fiap.infrastructure.presenters.OrderQrCodeItemApiPresenter;
import com.alura.fiap.infrastructure.presenters.wrapper.OrderQrCodeApiPresenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderQrCodeController implements OrderQrCodeAPI {

    private static final Logger logger = LoggerFactory.getLogger(OrderQrCodeController.class);
    private final CreateOrderQrCodeUseCase createOrderQrCodeUseCase;
    private final CreateImageQrCodeUseCase createImageQrCodeUseCase;


    public OrderQrCodeController(
            CreateOrderQrCodeUseCase createOrderQrCodeUseCase,
            CreateImageQrCodeUseCase createImageQrCodeUseCase) {
        this.createOrderQrCodeUseCase = createOrderQrCodeUseCase;
        this.createImageQrCodeUseCase = createImageQrCodeUseCase;
    }

    @Override
    public ResponseEntity<byte[]> createMerchantOrderQrCode(String authorization, String accessToken, CreateOrderQrCodeRequest request,
                                                                                String userId, String externalPosId) {
        try {
            var items = OrderQrCodeItemApiPresenter.present(request.items());
            var cashOut = OrderQrCodeCashOutApiPresenter.present(request.cashOut());

            var command = new CreateOrderQrCodeCommand(request.externalReference(),
                    request.title(),
                    request.notificationUrl(),
                    request.totalAmount(),
                    items,
                    cashOut,
                    request.description());

            var orderQrCode = this.createOrderQrCodeUseCase.execute(authorization, command, userId, externalPosId);
            final byte[] image = createImageQrCodeUseCase.execute(CreateImageQrCodeCommand.with(orderQrCode.qrData()));
            logger.info("Merchant order QR code created successfully for userId: {}, externalPosId: {}", userId, externalPosId);

            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
        } catch (CustomOrderQrCodeException e) {
            logger.error("Error processing merchant order QR code for userId: {}, externalPosId: {}", userId, externalPosId, e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new OrderQrCodeResponseWrapper(null).getMediaType());
        }
    }
}