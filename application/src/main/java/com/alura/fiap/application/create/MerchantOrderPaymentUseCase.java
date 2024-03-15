package com.alura.fiap.application.create;


import com.alura.fiap.domain.payments.*;
import com.mercadopago.client.merchantorder.MerchantOrderClient;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.merchantorder.MerchantOrder;
import com.mercadopago.resources.merchantorder.MerchantOrderPayment;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static com.alura.fiap.application.execeptions.HandlerException.handleException;

public class MerchantOrderPaymentUseCase {


    private static final Logger logger = LoggerFactory.getLogger(MerchantOrderPaymentUseCase.class);
    public static final String MERCHANT_ORDER = "merchant_order";
    private final String accessTokenSeller;
    private final MerchantOrderPaymentGateway merchantOrderPaymentGateway;
    private final SQSEventPublisherGateway sqsEventPublisherGateway;


    public MerchantOrderPaymentUseCase(MerchantOrderPaymentGateway merchantOrderPaymentGateway,
                                       @Value("${access.token.seller}") String accessTokenSeller, SQSEventPublisherGateway sqsEventPublisherGateway) {
        this.merchantOrderPaymentGateway = merchantOrderPaymentGateway;
        this.accessTokenSeller = accessTokenSeller;
        this.sqsEventPublisherGateway = sqsEventPublisherGateway;
    }

    public ResponseEntity<Object> execute(Long id, String topic) {
        List<Payment> payments;
        MerchantOrder merchantOrderResourceMP;
        try {
            if (StringUtils.equalsIgnoreCase(topic, MERCHANT_ORDER)) {
                MerchantOrderClient client = new MerchantOrderClient();
                merchantOrderResourceMP = client.get(id, MPRequestOptions.builder().accessToken(accessTokenSeller).build());

                boolean isPaysAttempt = !merchantOrderResourceMP.getPayments().isEmpty();
                if (isPaysAttempt) {
                    // This loop is used to iterate over payments, but currently, it doesn't perform any specific action.
                    merchantOrderResourceMP.getPayments().forEach(payment -> logger.info("Processing payment with ID: {}", payment.getId()));
                    payments = convertToPayment(merchantOrderResourceMP.getPayments().stream().toList());

                        this.merchantOrderPaymentGateway.saveMerchantOrderPayment(getMerchantOrder(merchantOrderResourceMP, payments));
                    logger.info("Save in Data Base : Order ID {}", merchantOrderResourceMP.getExternalReference());


                    //Consultando o pagamento no banco de dados
                    List<com.alura.fiap.domain.payments.MerchantOrder> merchantOrderPaymentByExternalReference =
                            this.merchantOrderPaymentGateway.findMerchantOrderPaymentByExternalReference(merchantOrderResourceMP.getExternalReference());
                    //Verificando se o pagamento foi aprovado e enviando o evento de status para o SQS
                    if(!merchantOrderPaymentByExternalReference.isEmpty() && merchantOrderPaymentByExternalReference.stream()
                            .anyMatch(merchantOrder -> merchantOrder.payment().get(0).status().equals("approved"))) {
                        OrderStatusProducer orderStatusProducer = OrderStatusProducer.with(
                                merchantOrderPaymentByExternalReference.get(0).externalReference(),
                                merchantOrderPaymentByExternalReference.get(0).payment().get(0).status());
                        sqsEventPublisherGateway.publishOrderStatus(orderStatusProducer);

                        PaymentStatusProducer paymentStatusProducer =
                                PaymentStatusProducer.with(merchantOrderPaymentByExternalReference.get(0).externalReference(),
                                        merchantOrderPaymentByExternalReference.get(0).title(),
                                        merchantOrderPaymentByExternalReference.get(0).payment().get(0).status(),
                                        merchantOrderPaymentByExternalReference.get(0).description());
                        sqsEventPublisherGateway.publishEventPaymentStatus(paymentStatusProducer);

                    }else {
                        //Caso o pagamento não tenha sido aprovado, envia o evento de status para o SQS apenas para o topic order-status
                        OrderStatusProducer orderStatusProducer = OrderStatusProducer.with(
                                merchantOrderPaymentByExternalReference.get(0).externalReference(),
                                merchantOrderPaymentByExternalReference.get(0).payment().get(0).status());
                        sqsEventPublisherGateway.publishOrderStatus(orderStatusProducer);
                    }
                } else {
                    logger.info("No payments found for merchant order with ID: {}", merchantOrderResourceMP.getId());
                }
            }
            return ResponseEntity.ok().build();

        } catch (MPException | MPApiException e) {
            // Handle Mercado Pago exceptions
            return handleException(e, id, topic, "Error processing merchant order payment. Please try again later.");
        } catch (Exception e) {
            // Handle other unexpected exceptions
            return handleException(e, id, topic, "Unexpected error processing merchant order payment. Please contact support.");
        }
    }

    private static com.alura.fiap.domain.payments.MerchantOrder getMerchantOrder(MerchantOrder merchantOrderResourceMP, List<Payment> paymentList) {
        return new com.alura.fiap.domain.payments.MerchantOrder(
                merchantOrderResourceMP.getId(),
                merchantOrderResourceMP.getStatus(),
                merchantOrderResourceMP.getExternalReference(),
                merchantOrderResourceMP.getItems().get(0).getTitle(),
                merchantOrderResourceMP.getItems().get(0).getDescription(),
                paymentList,
                merchantOrderResourceMP.getNotificationUrl(),
                merchantOrderResourceMP.getTotalAmount());
    }

    private static List<Payment> convertToPayment(List<MerchantOrderPayment> merchantOrderPayment) {
        List<Payment> payments = new ArrayList<>();
        merchantOrderPayment.forEach(pay -> payments.add(Payment.with(
                pay.getId(),
                pay.getTransactionAmount(),
                pay.getTotalPaidAmount(),
                pay.getShippingCost(),
                pay.getCurrencyId(),
                pay.getStatus(),
                pay.getStatusDetails(),
                pay.getOperationType(),
                String.valueOf(pay.getDateApproved()),
                String.valueOf(pay.getDateCreated()),
                String.valueOf(pay.getLastModified()),
                pay.getAmountRefunded()
        )));
        return payments;
    }


}