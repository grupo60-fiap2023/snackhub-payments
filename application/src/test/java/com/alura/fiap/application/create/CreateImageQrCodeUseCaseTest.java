package com.alura.fiap.application.create;

import com.alura.fiap.domain.payments.FileQrCodeGateway;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CreateImageQrCodeUseCaseTest {

    @Test
    void testExecute() {
        // Given
        FileQrCodeGateway fileQrCodeGateway = mock(FileQrCodeGateway.class);
        CreateImageQrCodeUseCase useCase = new CreateImageQrCodeUseCase(fileQrCodeGateway);
        CreateImageQrCodeCommand command = CreateImageQrCodeCommand.with("00020101021243650016COM.MERCADOLIBRE020130636c1810fdd-2747-4743-9281-c0235ca3dcd45204000053039865802BR5909Test Test6009SAO PAULO62070503***6304AA3B");
        byte[] expectedResult = new byte[]{};

        // Stubbing the behavior of the mock
        when(fileQrCodeGateway.createImageQRCode(command.data())).thenReturn(expectedResult);

        // When
        byte[] result = useCase.execute(command);

        // Then
        assertNotNull(result, "Result should not be null");
        assertEquals(expectedResult, result, "Result should match the expected byte array");

        // Verify that the gateway method was called with the correct argument
        verify(fileQrCodeGateway).createImageQRCode(command.data());
    }

}