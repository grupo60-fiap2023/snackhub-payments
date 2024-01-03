package com.alura.fiap.infrastructure.api;


import com.alura.fiap.application.CardTokenOutput;
import com.alura.fiap.infrastructure.models.PaymentCreateCardTokenRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
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

@RequestMapping(value = "/v1")
@Tag(name = "Create Card Token Payment MP")
public interface CardTokenAPI {

    @PostMapping(value = "/card_tokens",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create Card Token Send Payment MP", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<CardTokenOutput> createCardToken(
            @RequestHeader(required = false) @Schema(hidden = true) String authorization,
            @RequestParam @Schema(example = "TEST-33bc9262-80dd-45cf-a377-0483fdb4cdb6") String publicKey,
            @RequestBody @Valid PaymentCreateCardTokenRequest request
    ) throws MPException, MPApiException;
}
