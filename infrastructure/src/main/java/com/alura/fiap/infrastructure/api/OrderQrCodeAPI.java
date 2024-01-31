package com.alura.fiap.infrastructure.api;

import com.alura.fiap.infrastructure.models.CreateOrderQrCodeRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "mp/orders")
@Tag(name = "Mercado Pago Integration")
public interface OrderQrCodeAPI {

    @PostMapping(value = "createMerchantOrderQrCode/{userId}/{externalPosId}/qrs",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create Merchant Order QR Code MP", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<byte[]> createMerchantOrderQrCode(
            @RequestHeader(required = false) @Schema(hidden = true) String authorization,
            @RequestHeader() String accessToken,
            @RequestBody @Valid CreateOrderQrCodeRequest input,
            @PathVariable @Schema(description = "Id do usuário no app", example = "1589696702") String userId,
            @PathVariable @Schema(description = "Id da Loja(POS) vincalado a app", example = "SNACKBARPOTESTSELLER") String externalPosId);
}