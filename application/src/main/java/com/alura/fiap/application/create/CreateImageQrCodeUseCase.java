package com.alura.fiap.application.create;


import com.alura.fiap.application.UseCase;
import com.alura.fiap.domain.payments.FileQrCodeGateway;

public class CreateImageQrCodeUseCase extends UseCase<CreateImageQrCodeCommand, byte[]> {

    private final FileQrCodeGateway fileQrCodeGateway;

    public CreateImageQrCodeUseCase(FileQrCodeGateway fileQrCodeGateway) {
        this.fileQrCodeGateway = fileQrCodeGateway;
    }

    @Override
    public byte[] execute(CreateImageQrCodeCommand command) {
        return fileQrCodeGateway.createImageQRCode(command.data());
    }
}
