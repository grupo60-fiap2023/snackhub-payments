package com.alura.fiap.infrastructure.presenters;


import com.alura.fiap.application.create.CreateOrderQrCodeCashOutCommand;
import com.alura.fiap.infrastructure.models.OrderQrCodeCashOutRequest;
import org.junit.experimental.categories.Categories;

@Categories.ExcludeCategory
public interface OrderQrCodeCashOutApiPresenter {
    static CreateOrderQrCodeCashOutCommand present(OrderQrCodeCashOutRequest request) {
        return new CreateOrderQrCodeCashOutCommand(request.amount());
    }
}
