package com.alura.fiap.infrastructure.presenters.wrapper;

import com.alura.fiap.application.OrderQrCodeOutput;
import com.alura.fiap.infrastructure.models.OrderQrCodeResponse;

public interface OrderQrCodeApiPresenter {

    static OrderQrCodeResponseWrapper present(OrderQrCodeOutput orderQrCodeOutput) {
        OrderQrCodeResponse orderQrCodeResponse = new OrderQrCodeResponse(
                orderQrCodeOutput.inStoreOrderId(), orderQrCodeOutput.qrData());

        return new OrderQrCodeResponseWrapper(orderQrCodeResponse.qrData(), orderQrCodeResponse);
    }

    class OrderQrCodeResponseWrapper {
        private final OrderQrCodeResponse orderQrCodeResponse;
        private final String jpegImageBase64;

        public OrderQrCodeResponseWrapper(String jpegImageBase64, OrderQrCodeResponse orderQrCodeResponse) {
            this.jpegImageBase64 = jpegImageBase64;
            this.orderQrCodeResponse = orderQrCodeResponse;
        }

        public OrderQrCodeResponse getOrderQrCodeResponse() {
            return orderQrCodeResponse;
        }

        public String getJpegImageBase64() {
            return jpegImageBase64;
        }
    }
}
