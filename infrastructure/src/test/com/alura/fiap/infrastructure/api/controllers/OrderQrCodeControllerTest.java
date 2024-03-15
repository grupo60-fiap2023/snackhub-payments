package com.alura.fiap.infrastructure.api.controllers;

import com.alura.fiap.application.OrderQrCodeOutput;
import com.alura.fiap.application.create.CreateImageQrCodeUseCase;
import com.alura.fiap.application.create.CreateOrderQrCodeCommand;
import com.alura.fiap.application.create.CreateOrderQrCodeUseCase;
import com.alura.fiap.domain.payments.OrderQrCodeGateway;
import com.alura.fiap.infrastructure.models.CreateOrderQrCodeRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeCashOutRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeItemsRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeResponse;
import com.alura.fiap.infrastructure.presenters.wrapper.OrderQrCodeApiPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class OrderQrCodeControllerTest {

    @InjectMocks
    OrderQrCodeController orderQrCodeController;

    @Mock
    CreateOrderQrCodeUseCase createOrderQrCodeUseCase;
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
        // Criar uma instância real de OrderQrCodeOutput
        OrderQrCodeOutput orderQrCodeOutput = new OrderQrCodeOutput("inStoreOrderId", "qrData");

        // Configurar o comportamento do createOrderQrCodeUseCase para retornar a instância real
        when(createOrderQrCodeUseCase.execute(anyString(), any(CreateOrderQrCodeCommand.class), anyString(), anyString()))
                .thenReturn(orderQrCodeOutput);


        ResponseEntity<OrderQrCodeResponse> response = orderQrCodeController.createOrderQrCode(
                "auth",
                new CreateOrderQrCodeRequest(
                        "orderId",
                        "TEST-CUCUMBERQRCODE Product Order orderId",
                        Collections.singletonList(
                                new OrderQrCodeItemsRequest(
                                        "customerId",
                                        "unit",
                                        29.05,
                                        1,
                                        29.05,
                                        "identifierId")),
                        29.05,
                        new OrderQrCodeCashOutRequest(0.0),
                        "https://testsnackhubpay-mercadopago.ultrahook.com",
                        "TEST-CUCUMBERQRCODE Combo+Refil"),
                "userId", "posId");

        // Verificação se o método execute() foi chamado corretamente
        verify(createOrderQrCodeUseCase, times(1)).execute(anyString(), any(CreateOrderQrCodeCommand.class), anyString(), anyString());

        // Verificação se a resposta é um ResponseEntity com status de created
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }


}
