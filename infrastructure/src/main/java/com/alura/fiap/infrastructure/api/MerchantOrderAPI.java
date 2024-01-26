package com.alura.fiap.infrastructure.api;

import com.alura.fiap.domain.payments.MerchantOrder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "merchant/orders")

@Tag(name = "Merchant Orders")
public interface MerchantOrderAPI {
    @GetMapping(value = "receiveMerchantOrder/{orderId}")
    @Operation(summary = "Receive Merchant Order", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<MerchantOrder> receiveMerchantOrder(
            @PathVariable @Schema(description = "Order Id", example = "15127102346") Long orderId);
}