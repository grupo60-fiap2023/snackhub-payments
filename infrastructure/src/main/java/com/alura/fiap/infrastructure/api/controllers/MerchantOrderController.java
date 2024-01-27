package com.alura.fiap.infrastructure.api.controllers;

import com.alura.fiap.application.receive.FindMerchantOrderByExternalReferenceUseCase;
import com.alura.fiap.domain.payments.MerchantOrder;
import com.alura.fiap.infrastructure.api.MerchantOrderAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class MerchantOrderController implements MerchantOrderAPI {

    private final FindMerchantOrderByExternalReferenceUseCase findMerchantOrderByExternalReferenceUseCase;

    public MerchantOrderController(FindMerchantOrderByExternalReferenceUseCase findMerchantOrderByExternalReferenceUseCase) {
        this.findMerchantOrderByExternalReferenceUseCase = findMerchantOrderByExternalReferenceUseCase;
    }

    @Override
    public ResponseEntity<Optional<List<MerchantOrder>>> receiveMerchantOrder(String externalReference) {
        return ResponseEntity.ok(this.findMerchantOrderByExternalReferenceUseCase.execute(externalReference));
    }
}
