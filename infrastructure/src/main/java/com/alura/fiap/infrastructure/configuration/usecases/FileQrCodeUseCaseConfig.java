package com.alura.fiap.infrastructure.configuration.usecases;

import com.alura.fiap.application.create.CreateImageQrCodeUseCase;
import com.alura.fiap.domain.payments.FileQrCodeGateway;
import org.junit.experimental.categories.Categories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Categories.ExcludeCategory
@Configuration
public class FileQrCodeUseCaseConfig {

    private final FileQrCodeGateway fileQrCodeGateway;

    public FileQrCodeUseCaseConfig(final FileQrCodeGateway fileQrCodeGateway) {
        this.fileQrCodeGateway = fileQrCodeGateway;
    }

    @Bean
    public CreateImageQrCodeUseCase createImageQrCodeUseCase() {
        return new CreateImageQrCodeUseCase(fileQrCodeGateway);
    }
}
