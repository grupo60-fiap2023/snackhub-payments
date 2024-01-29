package com.alura.fiap.application.create;

import com.alura.fiap.domain.payments.MerchantOrderPaymentGateway;
import com.mercadopago.client.merchantorder.MerchantOrderClient;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.merchantorder.MerchantOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MerchantOrderPaymentUseCaseTest {

    public static final String TOKEN = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    @Mock
    private MerchantOrderPaymentGateway merchantOrderPaymentGateway;
    @Mock
    private MerchantOrderClient mockClient;
    @InjectMocks
    private MerchantOrderPaymentUseCase merchantOrderPaymentUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        merchantOrderPaymentUseCase = new MerchantOrderPaymentUseCase(merchantOrderPaymentGateway, TOKEN);
    }

    @Test
    void testGetMerchantOrderById() throws MPException, MPApiException {
        // Create a mock MerchantOrder
        MerchantOrder mockMerchantOrder = createMockMerchantOrder();
        // Use the mock in your test
        Long orderId = 15273253461L;
        String topic = "merchant_order";
        MPRequestOptions expectedRequestOptions = MPRequestOptions.builder().build();

        // Set behavior for the mockClient when calling get method with any Long and any RequestOptions
        Mockito.when(mockClient.get(orderId, expectedRequestOptions)).thenReturn(mockMerchantOrder);

        // Mock the saveMerchantOrderPayment method for a void method
        doNothing().when(merchantOrderPaymentGateway).saveMerchantOrderPayment(any());

        ResponseEntity<?> response = merchantOrderPaymentUseCase.execute(orderId, topic);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue()); // Assuming you return 200 OK for success

    }

    @Test
    void testExecuteWithInvalidTopic() {
        // Arrange
        Long orderId = 123L;
        String topic = "invalid_topic";

        // Act
        ResponseEntity<?> response = merchantOrderPaymentUseCase.execute(orderId, topic);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue()); // Assuming you return 200 OK for invalid topic
        // Add more assertions based on your specific requirements
    }

    // Create a helper method to generate a mock MerchantOrder
    private MerchantOrder createMockMerchantOrder() {
        // Create a mock of the MerchantOrder class
        MerchantOrder mockMerchantOrder = mock(MerchantOrder.class);

        // Set values for different properties
        when(mockMerchantOrder.getId()).thenReturn(15273253461L);
        when(mockMerchantOrder.getDateCreated()).thenReturn(OffsetDateTime.parse("2024-01-27T12:18:07.914-04:00"));
        // ... Continue setting other nested objects and properties

        return mockMerchantOrder;
    }

}