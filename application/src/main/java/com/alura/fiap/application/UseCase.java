package com.alura.fiap.application;

import org.junit.experimental.categories.Categories;

@Categories.ExcludeCategory
public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN inputObject);
}
