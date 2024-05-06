/*
 * AccountBalanceService.java
 *
 * Acme Account Manager is the account manager of Acme Bank.
 *
 * This class or interface is part of the Acme Account Manager project.
 * The class or interface must not be used outside of this context.
 */
package com.acmebank.accountmanager.controller;

import com.acmebank.accountmanager.response.AccountBalanceResponse;
import com.acmebank.accountmanager.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * The service for <code>AccountBalance</code>.
 *
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
@Tag(name = "Account Balance Service", description = "The Account Balance API")
@Service
public interface AccountBalanceService {

    /**
     * Returns the account balance for specific customer.
     * Takes in the <code>Void</code> as input.
     * Returns the <code>AccountBalanceResponse</code> upon success
     * or the <code>ErrorResponse</code> upon failure.
     *
     * @param req        the request entity containing the Void
     * @param customerId the customer ID
     * @return the response entity containing the AccountBalanceResponse upon success
     * or ErrorResponse upon failure
     */
    @Operation(
            summary = "Account balance for specific customer",
            description = "Return the account balance for specific customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Return the account balance for specific customer",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AccountBalanceResponse.class))}
            ),
            @ApiResponse(responseCode = "400,404",
                    description = "Any failed responses",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class))}
            )
    })
    @GetMapping(value = "/api/v1/balances/{cid}")
    ResponseEntity<?> accountBalances(RequestEntity<Void> req, @PathVariable("cid") String customerId);

}
