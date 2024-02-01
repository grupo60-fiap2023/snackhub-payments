package com.alura.fiap.infrastructure.gateway;

import com.alura.fiap.domain.payments.MerchantOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MerchantOrderMongoDBGatewayTest {

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private MerchantOrderMongoDBGateway merchantOrderMongoDBGateway;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveMerchantOrderPayment() {
        MerchantOrder merchantOrder = new MerchantOrder(15273253461L,
                "opened", "aWRfcGVkaWRv", Collections.emptyList(),
                "https://snackhubpay-mercadopago.ultrahook.com", BigDecimal.valueOf(29.05));
        // Criar um objeto MerchantOrder para teste

        merchantOrderMongoDBGateway.saveMerchantOrderPayment(merchantOrder);

        verify(mongoTemplate, times(1)).save(merchantOrder);
    }

    @Test
    public void testRemoveMerchantOrderPayment() {
        MerchantOrder merchantOrder = new MerchantOrder(15273253461L,
                "opened", "aWRfcGVkaWRv", Collections.emptyList(),
                "https://snackhubpay-mercadopago.ultrahook.com", BigDecimal.valueOf(29.05));
        // Criar um objeto MerchantOrder para teste

        merchantOrderMongoDBGateway.removeMerchantOrderPayment(merchantOrder);

        verify(mongoTemplate, times(1)).remove(merchantOrder);
    }

    @Test
    public void testFindMerchantOrderPaymentByExternalReference() {
        String externalReference = "TestExternalReference";
        List<MerchantOrder> expectedResult = Collections.singletonList(new MerchantOrder(15273253461L,
                "opened", "aWRfcGVkaWRv", Collections.emptyList(),
                "https://snackhubpay-mercadopago.ultrahook.com", BigDecimal.valueOf(29.05)));
        // Criar uma lista de MerchantOrder para teste

        Query query = new Query();
        query.addCriteria(Criteria.where("externalReference").is(externalReference));

        when(mongoTemplate.find(query, MerchantOrder.class)).thenReturn(expectedResult);

        List<MerchantOrder> result = merchantOrderMongoDBGateway.findMerchantOrderPaymentByExternalReference(externalReference);

        assertEquals(expectedResult, result);
        verify(mongoTemplate, times(1)).find(query, MerchantOrder.class);
    }
}
