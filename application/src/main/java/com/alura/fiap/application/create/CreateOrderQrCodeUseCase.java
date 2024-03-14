package com.alura.fiap.application.create;


import com.alura.fiap.application.AuthUseCase;
import com.alura.fiap.application.OrderQrCodeOutput;
import com.alura.fiap.domain.payments.OrderQrCode;
import com.alura.fiap.domain.payments.OrderQrCodeCashOut;
import com.alura.fiap.domain.payments.OrderQrCodeGateway;
import com.alura.fiap.domain.payments.OrderQrCodeItem;

import java.util.List;
import java.util.Objects;

public class CreateOrderQrCodeUseCase extends AuthUseCase<CreateOrderQrCodeCommand, OrderQrCodeOutput> {
    private final OrderQrCodeGateway orderQrCodeGateway;

    public CreateOrderQrCodeUseCase(final OrderQrCodeGateway orderQrCodeGateway) {
        this.orderQrCodeGateway = Objects.requireNonNull(orderQrCodeGateway);
    }

    @Override
    public OrderQrCodeOutput execute(String authorization,
                                     CreateOrderQrCodeCommand command,
                                     String userId,
                                     String externalPosId) {

        List<OrderQrCodeItem> items = command.items().stream()
                .map(item -> OrderQrCodeItem.with(
                        item.title(),
                        item.unitMeasure(),
                        item.unitPrice(),
                        item.quantity(),
                        item.totalAmount(),
                        item.description()
                )).toList();

        var cashOut = new OrderQrCodeCashOut(command.cashOut().amount());

        var orderQrCode = new OrderQrCode(command.externalReference(), command.title(), command.notificationUrl(),
                command.totalAmount(), items, cashOut, command.description());

        return OrderQrCodeOutput.from(this.orderQrCodeGateway.createOrderQRCode(authorization, orderQrCode, userId, externalPosId));
    }

}
