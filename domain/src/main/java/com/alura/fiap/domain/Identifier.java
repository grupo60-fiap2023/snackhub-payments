package com.alura.fiap.domain;

import org.junit.experimental.categories.Categories;

@Categories.ExcludeCategory
public abstract class Identifier  {
    public abstract Long getValue();
}