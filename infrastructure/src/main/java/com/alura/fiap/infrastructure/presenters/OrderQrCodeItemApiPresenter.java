 package com.alura.fiap.infrastructure.presenters;

import com.alura.fiap.application.OrderQrCodeOutput;
import com.alura.fiap.application.create.CreateOrderQrCodeItemCommand;
import com.alura.fiap.infrastructure.models.OrderQrCodeItemsRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeResponse;

import java.util.List;

public interface OrderQrCodeItemApiPresenter {

    static List<CreateOrderQrCodeItemCommand> present(List<OrderQrCodeItemsRequest> itemsRequest) {
        return itemsRequest.stream().map(
                        itemRequest -> CreateOrderQrCodeItemCommand.with(
                                itemRequest.id(),
                                itemRequest.skuNumber(),
                                itemRequest.category(),
                                itemRequest.title(),
                                itemRequest.description(),
                                itemRequest.unitPrice(),
                                itemRequest.quantity(),
                                itemRequest.unitMeasure(),
                                itemRequest.totalAmount()
                        ))
                .toList();
    }

    static OrderQrCodeResponse present(final OrderQrCodeOutput orderItem) {
        return new OrderQrCodeResponse(
                orderItem.inStoreOrderId(),
                orderItem.qrData());
    }
}
