package com.alura.fiap.application;

import org.junit.experimental.categories.Categories;

@Categories.ExcludeCategory
public abstract class AuthUseCase<IN, OUT> {

    public abstract OUT execute(String token, IN inputObject, String userId, String externalPosId);
}
