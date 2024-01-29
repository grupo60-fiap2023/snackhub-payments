package com.alura.fiap.application.create;


import com.alura.fiap.domain.payments.MerchantOrderPaymentGateway;
import com.alura.fiap.domain.payments.Payment;
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

import java.util.List;
import java.util.stream.Collectors;

import static com.alura.fiap.application.execeptions.HandlerException.handleException;

public class MerchantOrderPaymentUseCase {

    private static final Logger logger = LoggerFactory.getLogger(MerchantOrderPaymentUseCase.class);
    public static final String MERCHANT_ORDER = "merchant_order";
    private final String accessTokenSeller;
    private final MerchantOrderPaymentGateway merchantOrderPaymentGateway;

    public MerchantOrderPaymentUseCase(MerchantOrderPaymentGateway merchantOrderPaymentGateway,
                                       @Value("${access.token.seller}") String accessTokenSeller) {
        this.merchantOrderPaymentGateway = merchantOrderPaymentGateway;
        this.accessTokenSeller = accessTokenSeller;
    }

    public ResponseEntity<Object> execute(Long id, String topic) {
        List<List<Payment>> payments;
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

    private static com.alura.fiap.domain.payments.MerchantOrder getMerchantOrder(MerchantOrder merchantOrderResourceMP, List<List<Payment>> paymentList) {
        return new com.alura.fiap.domain.payments.MerchantOrder(
                merchantOrderResourceMP.getId(),
                merchantOrderResourceMP.getStatus(),
                merchantOrderResourceMP.getExternalReference(),
                paymentList,
                merchantOrderResourceMP.getNotificationUrl(),
                merchantOrderResourceMP.getTotalAmount());
    }

    private static List<List<Payment>> convertToPayment(List<MerchantOrderPayment> merchantOrderPayments) {
        return merchantOrderPayments.stream()
                .map(pay -> Payment.with(
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
                ))
                .collect(Collectors.toList());
    }




}