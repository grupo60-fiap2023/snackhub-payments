package com.alura.fiap.infrastructure.bdd.cucumber.steps;

import com.alura.fiap.application.create.MerchantOrderPaymentUseCase;
import com.alura.fiap.domain.payments.MerchantOrder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotificationSteps {

    private MerchantOrderPaymentUseCase merchantOrderPaymentUseCase;
    private MerchantOrder currentMerchantOrder;
    private boolean notificationReceived;

    @Given("a merchant order with id {long}")
    public void givenAMerchantOrderWithId(Long orderId) {
        currentMerchantOrder = createMerchantOrder(orderId);
        notificationReceived = false;
    }

    @When("a notification with topic {string} is received")
    public void whenANotificationWithTopicIsReceived(String topic) {
        // Simula a recepção de uma notificação
        if (topic.equals("merchant-order")) {
            notificationReceived = true;
        }
    }

    @Then("the payment for the order should be marked as successful")
    public void thenThePaymentShouldBeMarkedAsSuccessful() {
        // Verifica se a notificação foi recebida e marca o pagamento como bem-sucedido
        if (notificationReceived) {
            // Implemente a lógica real para marcar o pagamento como bem-sucedido
            assertEquals("success", currentMerchantOrder.status());
        }
    }

    // Método fictício para criar uma ordem de comerciante
    private MerchantOrder createMerchantOrder(Long orderId) {
        // Lógica de criação da ordem de comerciante
        return MerchantOrder.with(
                orderId,
                "opened",
                "testecucumberorder",
                Collections.emptyList(),
                "notificationtestecucumberorder",
                BigDecimal.ONE
        );
    }
}
