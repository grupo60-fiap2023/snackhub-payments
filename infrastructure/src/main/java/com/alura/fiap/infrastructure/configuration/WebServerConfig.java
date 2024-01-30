package com.alura.fiap.infrastructure.configuration;

import org.junit.experimental.categories.Categories;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Categories.ExcludeCategory
@Configuration
@ComponentScan("com.alura.fiap.infrastructure")
public class WebServerConfig {
}