package com.alura.fiap.infrastructure.api.controllers;

import com.alura.fiap.application.OrderQrCodeOutput;
import com.alura.fiap.application.create.CreateImageQrCodeUseCase;
import com.alura.fiap.application.create.CreateOrderQrCodeCommand;
import com.alura.fiap.application.create.CreateOrderQrCodeUseCase;
import com.alura.fiap.domain.payments.OrderQrCodeGateway;
import com.alura.fiap.domain.payments.OrderQrCodeOut;
import com.alura.fiap.infrastructure.models.CreateOrderQrCodeRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeCashOutRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeItemsRequest;
import com.alura.fiap.infrastructure.presenters.wrapper.OrderQrCodeApiPresenter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderQrCodeControllerTest {

    @InjectMocks
    OrderQrCodeController orderQrCodeController;

    @Mock
    CreateOrderQrCodeUseCase createOrderQrCodeUseCase;
    @Mock
    CreateImageQrCodeUseCase createImageQrCodeUseCase;
    @Mock
    OrderQrCodeGateway orderQrCodeGateway;
    @Mock
    OrderQrCodeApiPresenter orderQrCodeApiPresenter;


    @BeforeEach
    void setUp() {
        // Inicializar os mocks manualmente
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateMerchantOrderQrCode() {

//        CreateOrderQrCodeUseCase createOrderQrCodeUseCase = mock(CreateOrderQrCodeUseCase.class);

        when(createOrderQrCodeUseCase.execute(anyString(), any(CreateOrderQrCodeCommand.class), anyString(), anyString())).thenReturn(OrderQrCodeOutput.from(OrderQrCodeOut.createOrderQrCodeOut("8d2d0b8d-dcef-43d1-9f20-7ac300945f39", "00020101021243650016COM.MERCADOLIBRE0201306368d2d0b8d-dcef-43d1-9f20-7ac300945f395204000053039865802BR5908Snackhub6009SAO PAULO62070503***630425EC")));

        ResponseEntity<byte[]> response = this.orderQrCodeController.createMerchantOrderQrCode(
                "auth", "token",
                new CreateOrderQrCodeRequest(
                        "TEST-CUCUMBERQRCODEWRfcGVkaWRv",
                        "TEST-CUCUMBERQRCODE Product Order aWRfcGVkaWRv",
                        Collections.singletonList(
                                new OrderQrCodeItemsRequest(
                                        "Combo+Refil product Order aWRfcGVkaWRv",
                                        "unit",
                                        29.05,
                                        1,
                                        29.05
                                )
                        ),
                        29.05,
                        new OrderQrCodeCashOutRequest(0.0),
                        "https://testsnackhubpay-mercadopago.ultrahook.com",
                        "TEST-CUCUMBERQRCODE Combo+Refil"),
                "userId",
                "posId");

        // Verifique se o método do caso de uso foi chamado com os argumentos corretos
        verify(createOrderQrCodeUseCase).execute(eq("auth"), any(CreateOrderQrCodeCommand.class), eq("userId"), eq("posId"));

        // Verifique se a resposta não é nula
        Assertions.assertNotNull(response);
    }
}
