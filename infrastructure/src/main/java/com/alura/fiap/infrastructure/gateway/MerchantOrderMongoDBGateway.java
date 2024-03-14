package com.alura.fiap.infrastructure.gateway;

import com.alura.fiap.domain.payments.MerchantOrder;
import com.alura.fiap.domain.payments.OrderQrData;
import com.alura.fiap.domain.payments.MerchantOrderPaymentGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MerchantOrderMongoDBGateway implements MerchantOrderPaymentGateway {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MerchantOrderMongoDBGateway(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public void saveOrderConsumer(OrderQrData orderQrData) {
        this.mongoTemplate.save(orderQrData);
    }

    @Override
    public void saveMerchantOrderPayment(MerchantOrder merchantOrder) {
        this.mongoTemplate.save(merchantOrder);
    }

    @Override
    public void removeMerchantOrderPayment(MerchantOrder merchantOrder) {
        this.mongoTemplate.remove(merchantOrder);
    }

    public List<MerchantOrder> findMerchantOrderPaymentByExternalReference(String externalReference) {
        Query query = new Query();
        query.addCriteria(Criteria.where("externalReference").is(externalReference));
        return this.mongoTemplate.find(query, MerchantOrder.class);
    }

    @Override
    public List<OrderQrData> findQRDataPaymentByOrderId(String orderId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("orderId").is(orderId));
        return this.mongoTemplate.find(query, OrderQrData.class);
    }
}