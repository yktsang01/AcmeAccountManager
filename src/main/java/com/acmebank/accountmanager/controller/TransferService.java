/*
 * TransferService.java
 *
 * Acme Account Manager is the account manager of Acme Bank.
 *
 * This class or interface is part of the Acme Account Manager project.
 * The class or interface must not be used outside of this context.
 */
package com.acmebank.accountmanager.controller;

import com.acmebank.accountmanager.request.TransferFundRequest;
import com.acmebank.accountmanager.response.ErrorResponse;
import com.acmebank.accountmanager.response.TransferFundResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * The service for transferring funds.
 *
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
@Tag(name = "Transfer Service", description = "The Transfer API")
@Service
public interface TransferService {

    /**
     * Transfers funds.
     * Takes in the <code>TransferFundRequest</code> as input.
     * Returns the <code>TransferFundResponse</code> upon success
     * or the <code>ErrorResponse</code> upon failure.
     *
     * @param req the request entity containing the TransferFundRequest
     * @return the response entity containing the TransferFundResponse upon success
     * or ErrorResponse upon failure
     */
    @Operation(
            summary = "Fund transfer",
            description = "Transfer funds")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful operation",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TransferFundResponse.class))}
            ),
            @ApiResponse(responseCode = "400,404,406",
                    description = "Any failed responses",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class))}
            )
    })
    @PostMapping(value = "/api/v1/transfer"
            , consumes = {MediaType.APPLICATION_JSON_VALUE}
            , produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    ResponseEntity<?> transferFunds(@RequestBody RequestEntity<TransferFundRequest> req);

}
