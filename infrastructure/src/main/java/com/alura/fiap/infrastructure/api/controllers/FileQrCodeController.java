package com.alura.fiap.infrastructure.api.controllers;


import com.alura.fiap.application.create.CreateImageQrCodeCommand;
import com.alura.fiap.application.create.CreateImageQrCodeUseCase;
import com.alura.fiap.infrastructure.api.FileQrCodeAPI;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileQrCodeController implements FileQrCodeAPI {

    private final CreateImageQrCodeUseCase createImageQrCodeUseCase;

    public FileQrCodeController(CreateImageQrCodeUseCase createImageQrCodeUseCase) {
        this.createImageQrCodeUseCase = createImageQrCodeUseCase;
    }


    @Override
    public ResponseEntity<byte[]> createImageQrCode(String data) {

        final byte[] image = createImageQrCodeUseCase.execute(CreateImageQrCodeCommand.with(data));

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
    }
}
