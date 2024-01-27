package com.alura.fiap.infrastructure.persistence;

import com.alura.fiap.domain.payments.MerchantOrder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Document
public class DocumentMerchantOrder {
    @Id
    private Long id;
    @Indexed(unique = true)
    private final Long orderId;
    private final String status;
    private final String externalReference;
    private final List<DocumentPayment> documentPayments;
    private final String notificationUrl;
    private BigDecimal totalAmount;

    public DocumentMerchantOrder(Long orderId,
                                 String status,
                                 String externalReference,
                                 List<DocumentPayment> documentPayments,
                                 String notificationUrl,
                                 BigDecimal totalAmount) {
        this.orderId = orderId;
        this.status = status;
        this.externalReference = externalReference;
        this.documentPayments = documentPayments;
        this.notificationUrl = notificationUrl;
        this.totalAmount = totalAmount;
    }

    public static DocumentMerchantOrder create(final MerchantOrder merchantOrder) {
        return new DocumentMerchantOrder(merchantOrder.orderId(), merchantOrder.status(), merchantOrder.externalReference(),
                new ArrayList<>(), merchantOrder.notificationUrl(), merchantOrder.totalAmount());
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public List<DocumentPayment> getDocumentPayments() {
        return documentPayments;
    }

    public String getNotificationUrl() {
        return notificationUrl;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
