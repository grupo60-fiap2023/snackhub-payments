package com.alura.fiap.infrastructure.bdd.cucumber.steps;

import com.alura.fiap.application.receive.FindMerchantOrderByExternalReferenceUseCase;
import com.alura.fiap.application.receive.FindQrDataByOrderIdUseCase;
import com.alura.fiap.domain.payments.MerchantOrder;
import com.alura.fiap.infrastructure.api.controllers.MerchantOrderController;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class MerchantOrderStepDefinitions {

    private String externalReference;
    private ResponseEntity<List<MerchantOrder>> response;

    @Given("the external reference {string}")
    public void givenExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

    @When("the system receives a request to retrieve merchant orders")
    public void whenSystemReceivesRequest() {
        // Create a mock of FindMerchantOrderByExternalReferenceUseCase
        FindMerchantOrderByExternalReferenceUseCase mockUseCase =
                mock(FindMerchantOrderByExternalReferenceUseCase.class);

        FindQrDataByOrderIdUseCase mockQrDataUseCase =
                mock(FindQrDataByOrderIdUseCase.class);

        // Stub the execute method to return a predefined response
        ResponseEntity<List<MerchantOrder>> expectedResponse = createExpectedResponse();
        List<MerchantOrder> convertMerchantOrder =
                Objects.requireNonNull(expectedResponse.getBody()).stream().toList();

        Mockito.when(mockUseCase.execute(externalReference)).thenReturn(convertMerchantOrder);

        // Create a mock of MerchantOrderController
        MerchantOrderController controller= new MerchantOrderController(mockUseCase, mockQrDataUseCase);

        // Call the controller method to simulate the request
        response = controller.receiveMerchantOrder(externalReference);
    }

    private ResponseEntity<List<MerchantOrder>> createExpectedResponse() {
        return ResponseEntity.ok(Collections.singletonList(new MerchantOrder(15273253461L,
                "opened", "aWRfcGVkaWRv", "", "", Collections.emptyList(),
                "https://snackhubpay-mercadopago.ultrahook.com", BigDecimal.valueOf(29.05))));
    }

    @Then("the response should contain a list of merchant orders")
    public void thenResponseShouldContainList() {
        assertNotNull(response);
        assertNotNull(response.getBody());
        // Add more assertions based on your specific requirements
    }
}