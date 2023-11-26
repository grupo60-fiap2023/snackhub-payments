package com.alura.fiap.infrastructure.configuration.usecases;

import com.alura.fiap.application.create.CreateImageQrCodeUseCase;
import com.alura.fiap.domain.payments.FileQrCodeGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
