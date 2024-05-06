/*
 * TransferServiceControllerTests.java
 *
 * Acme Account Manager is the account manager of Acme Bank.
 *
 * This class or interface is part of the Acme Account Manager project.
 * The class or interface must not be used outside of this context.
 */
package com.acmebank.accountmanager.test.controller;

import com.acmebank.accountmanager.controller.TransferService;
import com.acmebank.accountmanager.request.TransferFundRequest;
import com.acmebank.accountmanager.response.ErrorResponse;
import com.acmebank.accountmanager.response.TransferFundResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Provides the test cases for <code>TransferService</code> and <code>TransferServiceController</code>.
 *
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
@SpringBootTest
public class TransferServiceControllerTests {

    /**
     * The transfer funds API endpoint.
     */
    private static final URI TRANSFER_FUNDS_URI = URI.create("/api/v1/transfer");
    /**
     * The mocked transfer service.
     */
    @MockBean
    private TransferService transferService;

    /**
     * Tests transfer funds for HTTP 200.
     */
    @Test
    public void transferFunds200() {
        TransferFundRequest mockedReq = new TransferFundRequest("123", "789",
                "XXX", BigDecimal.TEN);
        HttpHeaders mockedHeaders = new HttpHeaders();
        RequestEntity<TransferFundRequest> req =
                new RequestEntity<>(mockedReq, mockedHeaders, HttpMethod.POST, TRANSFER_FUNDS_URI);
        TransferFundResponse mockedResp = new TransferFundResponse("123", LocalDateTime.now(),
                "Transfer", "XXX", BigDecimal.TEN, "789", "Success");
        when(transferService.transferFunds(req))
                .thenAnswer(i -> ResponseEntity.status(HttpStatus.OK).body(mockedResp));

        ResponseEntity<?> resp = transferService.transferFunds(req);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
    }

    /**
     * Tests transfer funds for HTTP 400.
     */
    @Test
    public void transferFunds400() {
        TransferFundRequest mockedReq = new TransferFundRequest("123", "789",
                "", BigDecimal.TEN);
        HttpHeaders mockedHeaders = new HttpHeaders();
        RequestEntity<TransferFundRequest> req =
                new RequestEntity<>(mockedReq, mockedHeaders, HttpMethod.POST, TRANSFER_FUNDS_URI);
        ErrorResponse mockedResp = new ErrorResponse("Validation failed");
        when(transferService.transferFunds(req))
                .thenAnswer(i -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mockedResp));

        ResponseEntity<?> resp = transferService.transferFunds(req);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    /**
     * Tests transfer funds for HTTP 404.
     */
    @Test
    public void transferFunds404() {
        TransferFundRequest mockedReq = new TransferFundRequest("123", "789",
                "XXX", BigDecimal.TEN);
        HttpHeaders mockedHeaders = new HttpHeaders();
        RequestEntity<TransferFundRequest> req =
                new RequestEntity<>(mockedReq, mockedHeaders, HttpMethod.POST, TRANSFER_FUNDS_URI);
        ErrorResponse mockedResp = new ErrorResponse("Destination account not found");
        when(transferService.transferFunds(req))
                .thenAnswer(i -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(mockedResp));

        ResponseEntity<?> resp = transferService.transferFunds(req);
        assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
    }

    /**
     * Tests transfer funds for HTTP 406.
     */
    @Test
    public void transferFunds406() {
        TransferFundRequest mockedReq = new TransferFundRequest("123", "789",
                "XXX", BigDecimal.TEN);
        HttpHeaders mockedHeaders = new HttpHeaders();
        RequestEntity<TransferFundRequest> req =
                new RequestEntity<>(mockedReq, mockedHeaders, HttpMethod.POST, TRANSFER_FUNDS_URI);
        ErrorResponse mockedResp = new ErrorResponse("Insufficient funds in source account balance");
        when(transferService.transferFunds(req))
                .thenAnswer(i -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(mockedResp));

        ResponseEntity<?> resp = transferService.transferFunds(req);
        assertEquals(HttpStatus.NOT_ACCEPTABLE, resp.getStatusCode());
    }

}
