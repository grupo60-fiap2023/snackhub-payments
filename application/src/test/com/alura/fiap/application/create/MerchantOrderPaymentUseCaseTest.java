package com.alura.fiap.application.create;

import com.alura.fiap.domain.payments.MerchantOrder;
import com.alura.fiap.domain.payments.MerchantOrderPaymentGateway;
import com.alura.fiap.domain.payments.Payment;
import com.mercadopago.client.merchantorder.MerchantOrderClient;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MerchantOrderPaymentUseCaseTest {
    public static final String TOKEN = "TEST-3823682881313300-012901-0253d629a31919e10fb252ea3991a1e1-1589696702";
    @Mock
    MerchantOrderPaymentGateway merchantOrderPaymentGateway;
    @Mock
    MerchantOrderClient mockClient;
    @InjectMocks
    MerchantOrderPaymentUseCase merchantOrderPaymentUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        //merchantOrderPaymentUseCase = new MerchantOrderPaymentUseCase(merchantOrderPaymentGateway, TOKEN);
    }

    @Disabled
    @Test
    void testExecuteWithPayments() throws MPException, MPApiException {
        // Arrange
        Long id = 123L;
        String topic = "merchant_order";
        //MerchantOrder mockMerchantOrder = createMockMerchantOrderWithPayments(com.mercadopago.resources.merchantorder.MerchantOrder);

        MPRequestOptions build = MPRequestOptions.builder().accessToken(TOKEN).build();

        when(mockClient.get(anyLong(), build)).thenReturn(any(com.mercadopago.resources.merchantorder.MerchantOrder.class));

        // Act
        ResponseEntity<Object> response = merchantOrderPaymentUseCase.execute(id, topic);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(merchantOrderPaymentGateway, times(2)).saveMerchantOrderPayment(any());
    }

    @Disabled
    @Test
    void testExecuteWithoutPayments() throws MPException, MPApiException {
        // Arrange
        Long id = 123L;
        String topic = "merchant_order";
        //MerchantOrder mockMerchantOrder = createMockMerchantOrderWithoutPayments(com.mercadopago.resources.merchantorder.MerchantOrder);
        when(mockClient.get(anyLong(), any())).thenReturn(any(com.mercadopago.resources.merchantorder.MerchantOrder.class));

        // Act
        ResponseEntity<Object> response = merchantOrderPaymentUseCase.execute(id, topic);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(merchantOrderPaymentGateway, never()).saveMerchantOrderPayment(any());
    }


    @Test
    void testExecuteWithInvalidTopic() {
        // Arrange
        Long orderId = 123L;
        String invalidTopic = "invalid_topic";

        // Act
        ResponseEntity<Object> response = merchantOrderPaymentUseCase.execute(orderId, invalidTopic);

        // Assert
        assertEquals(200, response.getStatusCodeValue()); // Assuming you return 200 OK for invalid topic
        // Add more assertions based on your specific requirements
    }


    private MerchantOrder createMockMerchantOrderWithPayments(com.mercadopago.resources.merchantorder.MerchantOrder merchantOrder) {

       return MerchantOrder.with(
                111L,
                "",
                "",
                Payment.with(
                        323232L,
                        BigDecimal.ONE,
                        BigDecimal.ONE,
                        BigDecimal.ZERO,
                        "BRL",
                        "rejected",
                        "cc_rejected_other_reason",
                        "regular_payment",
                        "0001-01-01T00:00:00.000+00:00",
                        "0001-01-01T00:00:00.000+00:00",
                        "2024-01-28T15:53:10.000-04:00",
                        BigDecimal.ZERO
                ),
                "",
                BigDecimal.ONE
        );

    }

    private MerchantOrder createMockMerchantOrderWithoutPayments(
            com.mercadopago.resources.merchantorder.MerchantOrder merchantOrder) {
        return
                MerchantOrder.with(
                merchantOrder.getId(),
                merchantOrder.getStatus(),
                merchantOrder.getExternalReference(),
                Collections.emptyList(),
                merchantOrder.getNotificationUrl(),
                merchantOrder.getTotalAmount()
        );
    }
}