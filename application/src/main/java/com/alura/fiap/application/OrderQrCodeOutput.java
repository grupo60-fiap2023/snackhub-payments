 package com.alura.fiap.application;


 import com.alura.fiap.domain.payments.OrderQrCodeOut;
 import org.junit.experimental.categories.Categories;

 @Categories.ExcludeCategory
 public record OrderQrCodeOutput(String inStoreOrderId, String qrData) {

    public static OrderQrCodeOutput from(OrderQrCodeOut orderQrCodeOut) {
        return new OrderQrCodeOutput(orderQrCodeOut.inStoreOrderId(), orderQrCodeOut.qrData());
    }
}

