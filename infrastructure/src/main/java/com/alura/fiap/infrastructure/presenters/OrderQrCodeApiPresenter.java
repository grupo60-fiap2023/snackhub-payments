package com.alura.fiap.infrastructure.presenters;

import com.alura.fiap.application.OrderQrCodeOutput;
import com.alura.fiap.infrastructure.models.OrderQrCodeResponse;

public interface OrderQrCodeApiPresenter {
    static OrderQrCodeResponse present(OrderQrCodeOutput orderQrCodeOutput) {
        return new OrderQrCodeResponse(orderQrCodeOutput.inStoreOrderId(), orderQrCodeOutput.qrData());
    }
}
