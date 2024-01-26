package com.alura.fiap.infrastructure.gateway;

import com.alura.fiap.domain.payments.MerchantOrder;
import com.alura.fiap.domain.payments.MerchantOrderPaymentGateway;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MerchantOrderMongoDBGateway implements MerchantOrderPaymentGateway {

    private final MongoTemplate mongoTemplate;

    public MerchantOrderMongoDBGateway(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }



    @Override
    public void saveMerchantOrderPayment(MerchantOrder merchantOrder) {
        this.mongoTemplate.save(merchantOrder);
    }

    @Override
    public void removeMerchantOrderPayment(MerchantOrder merchantOrder) {
        this.mongoTemplate.remove(merchantOrder);
    }

    public MerchantOrder findMerchantOrderPayment(Long id) {
        return this.mongoTemplate.findById(id, MerchantOrder.class);
    }
}