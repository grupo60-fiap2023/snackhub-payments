package com.alura.fiap.application.execeptions;

import org.junit.experimental.categories.Categories;

@Categories.ExcludeCategory
public class MerchantOrderNotFoundException extends RuntimeException {

    public MerchantOrderNotFoundException(String message) {
        super(message);
    }
}

