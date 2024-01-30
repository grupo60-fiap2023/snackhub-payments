package com.alura.fiap.domain.payments;

import org.junit.experimental.categories.Categories;

@Categories.ExcludeCategory
public interface FileQrCodeGateway {

    byte[] createImageQRCode(String data);
}
