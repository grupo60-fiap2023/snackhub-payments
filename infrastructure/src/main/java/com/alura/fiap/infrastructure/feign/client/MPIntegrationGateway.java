package com.alura.fiap.infrastructure.feign.client;

import com.alura.fiap.infrastructure.configuration.FeignConfig;
import com.alura.fiap.infrastructure.models.CreateOrderQrCodeRequest;
import com.alura.fiap.infrastructure.models.OrderQrCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "mp", url = "${mp.url}", configuration = FeignConfig.class
)
public interface MPIntegrationGateway {

    @PostMapping(value = "/instore/orders/qr/seller/collectors/{userId}/pos/{externalPosId}/qrs")
    ResponseEntity<OrderQrCodeResponse> createOrderQRCode(@RequestHeader("Authorization") String authorization,
                                                          @RequestBody CreateOrderQrCodeRequest request,
                                                          @PathVariable String userId,
                                                          @PathVariable String externalPosId);

}
