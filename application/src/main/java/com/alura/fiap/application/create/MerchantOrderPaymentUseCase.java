package com.alura.fiap.application.create;


import com.alura.fiap.domain.payments.MerchantOrderPaymentGateway;
import com.alura.fiap.domain.payments.Payment;
import com.mercadopago.client.merchantorder.MerchantOrderClient;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.merchantorder.MerchantOrder;
import com.mercadopago.resources.merchantorder.MerchantOrderPayment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class MerchantOrderPaymentUseCase {

    private final MerchantOrderPaymentGateway merchantOrderPaymentGateway;
    public static final String MERCHANT_ORDER = "merchant_order";
    @Value("${access.token.seller}")
    String accessTokenSeller;

    public MerchantOrderPaymentUseCase(MerchantOrderPaymentGateway merchantOrderPaymentGateway) {
        this.merchantOrderPaymentGateway = merchantOrderPaymentGateway;
    }

    public ResponseEntity<?> execute(Long id, String topic) throws MPException, MPApiException {
        List<Payment> payments;
        MerchantOrder merchantOrderResourceMP;

        if (topic != null && topic.equalsIgnoreCase(MERCHANT_ORDER)) {
            MerchantOrderClient client = new MerchantOrderClient();
            merchantOrderResourceMP = client.get(id, MPRequestOptions.builder().accessToken(accessTokenSeller).build());

            boolean isPaysAttempt = merchantOrderResourceMP != null && !merchantOrderResourceMP.getPayments().isEmpty();
            if (isPaysAttempt) {
                merchantOrderResourceMP.getPayments().forEach(MerchantOrderPayment::getId);
                payments = converterToPayment(merchantOrderResourceMP.getPayments().stream().toList());
                this.merchantOrderPaymentGateway.saveMerchantOrderPayment(getMerchantOrder(merchantOrderResourceMP, payments));
            } else {
                this.merchantOrderPaymentGateway.saveMerchantOrderPayment(getMerchantOrder(merchantOrderResourceMP, new ArrayList<>()));
            }
        }
        return ResponseEntity.ok().build();
    }

    private static com.alura.fiap.domain.payments.MerchantOrder getMerchantOrder(MerchantOrder merchantOrderResourceMP, List<Payment> paymentList) {
        return new com.alura.fiap.domain.payments.MerchantOrder(
                merchantOrderResourceMP.getId(),
                merchantOrderResourceMP.getStatus(),
                merchantOrderResourceMP.getExternalReference(),
                paymentList,
                merchantOrderResourceMP.getNotificationUrl(),
                merchantOrderResourceMP.getTotalAmount());
    }

    private static List<Payment> converterToPayment(List<MerchantOrderPayment> merchantOrderPayment) {
        List<Payment> payments = new ArrayList<>();
        merchantOrderPayment.forEach(pay -> payments.add(Payment.with(
                pay.getId(),
                pay.getTransactionAmount(),
                pay.getTotalPaidAmount(),
                pay.getShippingCost(), pay.getCurrencyId(),
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