package com.alura.fiap.application.create;

import java.util.List;

public class CreateOrderQrCodeCommand {

    private final String externalReference;
    private final String title;
    private final String notificationUrl;
    private final Double totalAmount;
    private final List<CreateOrderQrCodeItemCommand> items;
    private final CreateOrderQrCodeCashOutCommand cashOut;
    private final String description;

    public CreateOrderQrCodeCommand(
            String externalReference,
            String title,
            String notificationUrl,
            Double totalAmount,
            List<CreateOrderQrCodeItemCommand> items,
            CreateOrderQrCodeCashOutCommand cashOut,
            String description
    ) {
        this.externalReference = externalReference;
        this.title = title;
        this.notificationUrl = notificationUrl;
        this.totalAmount = totalAmount;
        this.items = items;
        this.cashOut = cashOut;
        this.description = description;
    }

    // Métodos de acesso (getters)

    public String getExternalReference() {
        return externalReference;
    }

    public String getTitle() {
        return title;
    }

    public String getNotificationUrl() {
        return notificationUrl;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public List<CreateOrderQrCodeItemCommand> getItems() {
        return items;
    }

    public CreateOrderQrCodeCashOutCommand getCashOut() {
        return cashOut;
    }

    public String getDescription() {
        return description;
    }

    // Outros métodos, se necessário
}
