package com.alura.fiap.infrastructure.presenters;


import com.alura.fiap.application.create.CreateOrderQrCodeCashOutCommand;
import com.alura.fiap.infrastructure.models.OrderQrCodeCashOutRequest;

public interface OrderQrCodeCashOutApiPresenter {
    static CreateOrderQrCodeCashOutCommand present(OrderQrCodeCashOutRequest request) {
        return new CreateOrderQrCodeCashOutCommand(request.amount());
    }
}
