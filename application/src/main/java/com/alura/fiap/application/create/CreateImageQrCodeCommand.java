package com.alura.fiap.application.create;

import org.junit.experimental.categories.Categories;

@Categories.ExcludeCategory
public record CreateImageQrCodeCommand(String data) {

    public static CreateImageQrCodeCommand with(final String data) {
        return new CreateImageQrCodeCommand(data);
    }
}
