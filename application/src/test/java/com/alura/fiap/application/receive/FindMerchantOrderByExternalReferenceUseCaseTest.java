package com.alura.fiap.application.receive;

import com.alura.fiap.application.execeptions.MerchantOrderNotFoundException;
import com.alura.fiap.domain.payments.MerchantOrder;
import com.alura.fiap.domain.payments.MerchantOrderPaymentGateway;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindMerchantOrderByExternalReferenceUseCaseTest {

    @Test
    void testExecute_MerchantOrdersFound() throws MerchantOrderNotFoundException {
        // Arrange
        MerchantOrderPaymentGateway mockGateway = mock(MerchantOrderPaymentGateway.class);

        List<MerchantOrder> expectedMerchantOrders = Collections.singletonList(new MerchantOrder(1L,
                "approved", "ABC123", Collections.emptyList(), "notificationUrl", BigDecimal.ONE));
        when(mockGateway.findMerchantOrderPaymentByExternalReference(anyString())).thenReturn(expectedMerchantOrders);

        FindMerchantOrderByExternalReferenceUseCase useCase = new FindMerchantOrderByExternalReferenceUseCase(mockGateway);

        // Act
        List<MerchantOrder> resultMerchantOrders = useCase.execute("externalReference");

        // Assert
        assertEquals(expectedMerchantOrders, resultMerchantOrders);
    }

    @Test
    void testExecute_NoMerchantOrdersFound() {
        // Arrange
        MerchantOrderPaymentGateway mockGateway = mock(MerchantOrderPaymentGateway.class);
        when(mockGateway.findMerchantOrderPaymentByExternalReference(anyString())).thenReturn(Collections.emptyList());

        FindMerchantOrderByExternalReferenceUseCase useCase = new FindMerchantOrderByExternalReferenceUseCase(mockGateway);

        // Act and Assert
        assertThrows(MerchantOrderNotFoundException.class, () -> useCase.execute("externalReference"));
    }

}