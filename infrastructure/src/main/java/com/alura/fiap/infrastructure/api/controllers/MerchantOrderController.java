package com.alura.fiap.infrastructure.api.controllers;

import com.alura.fiap.application.receive.FindMerchantOrderByIdUseCase;
import com.alura.fiap.domain.payments.MerchantOrder;
import com.alura.fiap.infrastructure.api.MerchantOrderAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MerchantOrderController implements MerchantOrderAPI {

    private final FindMerchantOrderByIdUseCase findMerchantOrderByIdUseCase;

    public MerchantOrderController(FindMerchantOrderByIdUseCase findMerchantOrderByIdUseCase) {
        this.findMerchantOrderByIdUseCase = findMerchantOrderByIdUseCase;
    }

    @Override
    public ResponseEntity<MerchantOrder> receiveMerchantOrder(Long orderId) {
        return ResponseEntity.ok(this.findMerchantOrderByIdUseCase.execute(orderId));
    }
}
