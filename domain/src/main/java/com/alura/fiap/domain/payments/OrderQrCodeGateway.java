package com.alura.fiap.domain.payments;

import org.junit.experimental.categories.Categories;

@Categories.ExcludeCategory
public interface OrderQrCodeGateway {

    OrderQrCodeOut createOrderQRCode(String authorization, OrderQrCode request, String userId, String externalPosId);
}
