/*
 * AccountBalanceServiceControllerTests.java
 *
 * Acme Account Manager is the account manager of Acme Bank.
 *
 * This class or interface is part of the Acme Account Manager project.
 * The class or interface must not be used outside of this context.
 */
package com.acmebank.accountmanager.test.controller;

import com.acmebank.accountmanager.controller.AccountBalanceService;
import com.acmebank.accountmanager.response.AccountBalanceResponse;
import com.acmebank.accountmanager.response.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;

import java.math.BigDecimal;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Provides the test cases for <code>AccountBalanceService</code> and <code>AccountBalanceServiceController</code>.
 *
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
@SpringBootTest
public class AccountBalanceServiceControllerTests {

    /**
     * The account balances with customer ID API endpoint.
     */
    private static final URI ACCOUNT_BALANCES_CUSTOMER_URI = URI.create("/api/v1/balances/123");
    /**
     * The mocked account balance service.
     */
    @MockBean
    private AccountBalanceService acctBalService;

    /**
     * Tests account balances with customer ID for HTTP 200.
     */
    @Test
    public void accountBalancesWithCustomerId200() {
        HttpHeaders mockedHeaders = new HttpHeaders();
        RequestEntity<Void> req =
                new RequestEntity<>(mockedHeaders, HttpMethod.GET, ACCOUNT_BALANCES_CUSTOMER_URI);
        AccountBalanceResponse mockedResp = new AccountBalanceResponse("XXX", BigDecimal.TEN);
        when(acctBalService.accountBalances(req, "123"))
                .thenAnswer(i -> ResponseEntity.status(HttpStatus.OK).body(mockedResp));

        ResponseEntity<?> resp = acctBalService.accountBalances(req, "123");
        assertEquals(HttpStatus.OK, resp.getStatusCode());
    }

    /**
     * Tests account balances with customer ID for HTTP 400.
     */
    @Test
    public void accountBalancesWithCustomerId400() {
        HttpHeaders mockedHeaders = new HttpHeaders();
        RequestEntity<Void> req =
                new RequestEntity<>(mockedHeaders, HttpMethod.GET, ACCOUNT_BALANCES_CUSTOMER_URI);
        ErrorResponse mockedResp = new ErrorResponse("Validation failed");
        when(acctBalService.accountBalances(req, ""))
                .thenAnswer(i -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mockedResp));

        ResponseEntity<?> resp = acctBalService.accountBalances(req, "");
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    /**
     * Tests account balances with customer ID for HTTP 404.
     */
    @Test
    public void accountBalancesWithCustomerId404() {
        HttpHeaders mockedHeaders = new HttpHeaders();
        RequestEntity<Void> req =
                new RequestEntity<>(mockedHeaders, HttpMethod.GET, ACCOUNT_BALANCES_CUSTOMER_URI);
        ErrorResponse mockedResp = new ErrorResponse("Account balance not found");
        when(acctBalService.accountBalances(req, "123"))
                .thenAnswer(i -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(mockedResp));

        ResponseEntity<?> resp = acctBalService.accountBalances(req, "123");
        assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
    }

}
