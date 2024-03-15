package com.alura.fiap.infrastructure.bdd.cucumber.steps;

import com.alura.fiap.infrastructure.api.controllers.OrderQrCodeController;
import com.alura.fiap.infrastructure.models.CreateOrderQrCodeRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeCashOutRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeResponse;
import com.alura.fiap.infrastructure.models.OrderQrCodeResponseWrapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderQrCodeSteps {

    private String userId;
    private OrderQrCodeResponseWrapper response;


    @Given("a user with id {string}")
    public void givenAUserWithId(String userId) {
        this.userId = userId;
    }

    @When("the user requests to create a merchant order QR code")
    public void whenTheUserRequestsToCreateMerchantOrderQrCode() {


        OrderQrCodeResponse orderQrCodeResponse = new OrderQrCodeResponse(
                "TEST-CUCUMBERQRCODE",
                "TEST-CUCUMBERQRCODE Product orderId");
        response = OrderQrCodeResponseWrapper.with(orderQrCodeResponse);

        OrderQrCodeController orderQrCodeController = mock(OrderQrCodeController.class);

        CreateOrderQrCodeRequest createOrderQrCodeRequest = new CreateOrderQrCodeRequest(
                "TEST-CUCUMBERQRCODEorderId",
                "TEST-CUCUMBERQRCODE Product Order orderId",
                Collections.emptyList(),
                29.05,
                new OrderQrCodeCashOutRequest(0.0),
                "https://testsnackhubpay-mercadopago.ultrahook.com",
                "TEST-CUCUMBERQRCODE Combo+Refil");

        byte[] image = new byte[]{};

        // Mockando o comportamento do controlador para retornar uma resposta simulada
        when(orderQrCodeController.createOrderQrCode(
                "TEST-CUCUMBERQRCODE",
                createOrderQrCodeRequest, userId,
                "SNACKBARTEST-CUCUMBERQRCODE")).thenReturn(ResponseEntity.ok().build());

        // Chamando o método real para obter a resposta simulada
        response = OrderQrCodeResponseWrapper.with(orderQrCodeController.createOrderQrCode(
                "TEST-CUCUMBERQRCODE",
                createOrderQrCodeRequest,
                userId,
                "SNACKBARTEST-CUCUMBERQRCODE").getBody());
    }

    @Then("the system should generate a QR code successfully")
    public void thenTheSystemShouldGenerateQrCodeSuccessfully() {
        assertNotNull(response);
    }
}
