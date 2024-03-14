package com.alura.fiap.application.create;

import com.alura.fiap.application.OrderQrCodeOutput;
import com.alura.fiap.domain.payments.OrderQrCode;
import com.alura.fiap.domain.payments.OrderQrCodeGateway;
import com.alura.fiap.domain.payments.OrderQrCodeOut;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateOrderQrCodeUseCaseTest {
    public static final String qrData = "00020101021243650016COM.MERCADOLIBRE0201306368d2d0b8d-dcef-43d1-9f20-7ac300945f395204000053039865802BR5908Snackhub6009SAO PAULO62070503***630425EC";

    @Test
    void testExecute() {
        // Given
        OrderQrCodeGateway orderQrCodeGateway = mock(OrderQrCodeGateway.class);
        CreateOrderQrCodeUseCase useCase = new CreateOrderQrCodeUseCase(orderQrCodeGateway);

        CreateOrderQrCodeItemCommand fakeItem = CreateOrderQrCodeItemCommand.with(
                "Fake Title",
                "Fake Unit Measure",
                5.0,  // Fake unit price
                3,    // Fake quantity
                15.0,  // Fake total amount
                "Fake Description"
        );

        // Mocking command data
        CreateOrderQrCodeCommand command = new CreateOrderQrCodeCommand(
                "unittestaWRfcGVkaWRv", "Unit test Product Order aWRfcGVkaWRv", "https://testsnackhubpay-mercadopago.ultrahook.com",
                29.05, Collections.singletonList(fakeItem), new CreateOrderQrCodeCashOutCommand(0.0), "test unit Combo+Refil");

        // Mocking the result from the gateway
        OrderQrCodeOutput orderQrCodeOutput = OrderQrCodeOutput.from(new OrderQrCodeOut("8d2d0b8d-dcef-43d1-9f20-7ac300945f39", qrData));

        when(orderQrCodeGateway.createOrderQRCode(any(), any(OrderQrCode.class), any(), any())).thenReturn(OrderQrCodeOut.createOrderQrCodeOut(orderQrCodeOutput.inStoreOrderId(), orderQrCodeOutput.qrData()));

        // When
        OrderQrCodeOutput result = useCase.execute("authorization", command, "userId", "externalPosId");

        // Then
        assertNotNull(result, "Result should not be null");
        // Add more assertions based on your specific expectations

        // Verify that the gateway method was called with the correct arguments
        verify(orderQrCodeGateway).createOrderQRCode(eq("authorization"), any(OrderQrCode.class), eq("userId"), eq("externalPosId"));
    }
}