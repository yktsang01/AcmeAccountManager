/*
 * AccountBalanceServiceController.java
 *
 * Acme Account Manager is the account manager of Acme Bank.
 *
 * This class or interface is part of the Acme Account Manager project.
 * The class or interface must not be used outside of this context.
 */
package com.acmebank.accountmanager.controller;

import com.acmebank.accountmanager.entity.AccountBalance;
import com.acmebank.accountmanager.entity.AccountBalanceRepository;
import com.acmebank.accountmanager.response.AccountBalanceResponse;
import com.acmebank.accountmanager.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

/**
 * The API controller for implementing <code>AccountBalanceService</code>.
 *
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
@RestController
public class AccountBalanceServiceController implements AccountBalanceService {

    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(AccountBalanceServiceController.class);
    /**
     * The account balance repository.
     */
    @Autowired
    private AccountBalanceRepository acctBalRepo;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<?> accountBalances(RequestEntity<Void> req, String customerId) {

        if (Objects.isNull(customerId)
                || customerId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Validation failed"));
        }

        Optional<AccountBalance> optBalance = acctBalRepo.findById(customerId);
        if (optBalance.isPresent()) {
            AccountBalance balance = optBalance.get();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new AccountBalanceResponse(balance.getCurrency(), balance.getBalance()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Account balance not found for customer ID " + customerId));
        }
    }

}
