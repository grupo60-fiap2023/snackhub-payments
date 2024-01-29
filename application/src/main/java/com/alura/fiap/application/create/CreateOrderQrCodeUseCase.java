package com.alura.fiap.application.create;


import com.alura.fiap.application.AuthUseCase;
import com.alura.fiap.application.OrderQrCodeOutput;
import com.alura.fiap.domain.payments.OrderQrCode;
import com.alura.fiap.domain.payments.OrderQrCodeCashOut;
import com.alura.fiap.domain.payments.OrderQrCodeGateway;
import com.alura.fiap.domain.payments.OrderQrCodeItem;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CreateOrderQrCodeUseCase extends AuthUseCase<CreateOrderQrCodeCommand, OrderQrCodeOutput> {
    private final OrderQrCodeGateway orderQrCodeGateway;

    public CreateOrderQrCodeUseCase(final OrderQrCodeGateway orderQrCodeGateway) {
        this.orderQrCodeGateway = Objects.requireNonNull(orderQrCodeGateway);
    }

    @Override
    public OrderQrCodeOutput execute(String authorization, CreateOrderQrCodeCommand command, String userId, String externalPosId) {

        List<OrderQrCodeItem> items = command.getItems().stream()
                .map(item -> OrderQrCodeItem.with(
                        item.title(),
                        item.unitMeasure(),
                        item.unitPrice(),
                        item.quantity(),
                        item.totalAmount()
                ))
                .collect(Collectors.toList());

        var cashOut = new OrderQrCodeCashOut(command.getCashOut().amount());

        var orderQrCode = new OrderQrCode(command.getExternalReference(), command.getTitle(), command.getNotificationUrl(),
                command.getTotalAmount(), items, cashOut, command.getDescription());

        return OrderQrCodeOutput.from(this.orderQrCodeGateway.createOrderQRCode(authorization, orderQrCode, userId, externalPosId));
    }
}
