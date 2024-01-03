package com.alura.fiap.infrastructure.feign.client;

import com.alura.fiap.infrastructure.configuration.FeignConfig;
import com.alura.fiap.infrastructure.models.CreateOrderQrCodeRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeResponse;
import com.alura.fiap.infrastructure.models.PaymentCreateCardTokenRequest;
import com.mercadopago.resources.CardToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "mp", url = "${mp.url}", configuration = FeignConfig.class)
public interface MPIntegrationGateway {

    @PostMapping(value = "/instore/orders/qr/seller/collectors/{userId}/pos/{externalPosId}/qrs")
    ResponseEntity<OrderQrCodeResponse> createOrderQRCode(@RequestHeader("Authorization") String authorization,
                                                          @RequestBody CreateOrderQrCodeRequest request,
                                                          @PathVariable String userId,
                                                          @PathVariable String externalPosId);

    @PostMapping(value = "/v1/card_tokens")
    ResponseEntity<CardToken> createCardToken(@RequestHeader("Authorization") String authorization,
                                              @RequestBody PaymentCreateCardTokenRequest paymentCreateCardTokenRequest,
                                              @RequestParam String publicKey);


}
