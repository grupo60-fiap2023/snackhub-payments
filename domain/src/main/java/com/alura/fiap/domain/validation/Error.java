package com.alura.fiap.domain.validation;

import org.junit.experimental.categories.Categories;

@Categories.ExcludeCategory
public record Error(String message) {
}
