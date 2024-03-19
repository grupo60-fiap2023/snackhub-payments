package com.alura.fiap.infrastructure.api;

import com.alura.fiap.domain.payments.MerchantOrder;
import com.alura.fiap.domain.payments.OrderQrData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = "merchant/orders")
@Tag(name = "Merchant Orders")
public interface MerchantOrderAPI {
    @GetMapping(value = "receiveMerchantOrder/{externalReference}")
    @Operation(summary = "Receive Merchant Order by externalReference", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<List<MerchantOrder>> receiveMerchantOrder(
            @PathVariable @Schema(description = "Referência do pedido - externalReference do MP - OrderId do APP") String externalReference);



    @GetMapping(value = "receiveQrDataPayment/{orderId}")
    @Operation(summary = "Receive QRData by orderId", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<List<OrderQrData>> receiveQrDataPayment(
            @PathVariable @Schema(description = "Order Id") String orderId);
}