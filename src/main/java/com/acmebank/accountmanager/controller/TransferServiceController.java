/*
 * TransferServiceController.java
 *
 * Acme Account Manager is the account manager of Acme Bank.
 *
 * This class or interface is part of the Acme Account Manager project.
 * The class or interface must not be used outside of this context.
 */
package com.acmebank.accountmanager.controller;

import com.acmebank.accountmanager.entity.AccountBalance;
import com.acmebank.accountmanager.entity.AccountBalanceRepository;
import com.acmebank.accountmanager.entity.TransferTransaction;
import com.acmebank.accountmanager.entity.TransferTransactionRepository;
import com.acmebank.accountmanager.request.TransferFundRequest;
import com.acmebank.accountmanager.response.ErrorResponse;
import com.acmebank.accountmanager.response.TransferFundResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

/**
 * The API controller for implementing <code>TransferService</code>.
 *
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
@RestController
public class TransferServiceController implements TransferService {

    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(TransferServiceController.class);
    /**
     * The account balance repository.
     */
    @Autowired
    private AccountBalanceRepository acctBalRepo;
    /**
     * The transfer transaction repository.
     */
    @Autowired
    private TransferTransactionRepository transferTxnRepo;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<?> transferFunds(RequestEntity<TransferFundRequest> req) {

        if (req.hasBody() && Objects.nonNull(req.getBody())) {
            TransferFundRequest actualReq = req.getBody();

            if (Objects.isNull(actualReq.sourceCustomerId())
                    || actualReq.sourceCustomerId().isEmpty()
                    || Objects.isNull(actualReq.destinationCustomerId())
                    || actualReq.destinationCustomerId().isEmpty()
                    // source and destination cannot be same
                    || actualReq.sourceCustomerId().equals(actualReq.destinationCustomerId())
                    || Objects.isNull(actualReq.transferCurrency())
                    || actualReq.transferCurrency().isEmpty()
                    // only allow HKD transfer
                    || !actualReq.transferCurrency().equalsIgnoreCase("HKD")
                    || Objects.isNull(actualReq.transferAmount())
                    // transferAmount not positive (>0)
                    || actualReq.transferAmount().signum() != 1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponse("Validation failed"));
            }

            String fromAccount = actualReq.sourceCustomerId();
            String toAccount = actualReq.destinationCustomerId();
            String currency = actualReq.transferCurrency();
            BigDecimal amount = actualReq.transferAmount();

            Optional<AccountBalance> fromBalanceOpt = acctBalRepo.findById(fromAccount);
            Optional<AccountBalance> toBalanceOpt = acctBalRepo.findById(toAccount);

            if (fromBalanceOpt.isPresent() && toBalanceOpt.isPresent()) {
                AccountBalance fromBalance = fromBalanceOpt.get();
                AccountBalance toBalance = toBalanceOpt.get();

                // check currency matches in both source and destination
                if (!fromBalance.getCurrency().equalsIgnoreCase(toBalance.getCurrency())) {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ErrorResponse("Source and destination account currency not match"));
                }

                // check sufficient funds in account balance source
                // balance is less than request transferAmount
                if (fromBalance.getBalance().compareTo(amount) < 0) {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ErrorResponse("Insufficient funds to transfer"));
                }

                // do the transfer
                BigDecimal newFromAccountBalance = fromBalance.getBalance().subtract(amount);
                fromBalance.setBalance(newFromAccountBalance);
                BigDecimal newToAccountBalance = toBalance.getBalance().add(amount);
                toBalance.setBalance(newToAccountBalance);
                TransferTransaction transferTxn = new TransferTransaction(fromAccount, currency, amount, toAccount);
                transferTxnRepo.save(transferTxn);
                logger.info("transfer transaction created");
                acctBalRepo.save(fromBalance);
                logger.info("source account balance updated");
                acctBalRepo.save(toBalance);
                logger.info("destination account balance updated");

                return ResponseEntity.status(HttpStatus.OK)
                        .body(new TransferFundResponse(fromAccount, transferTxn.getTransactionDateTime(),
                                "Transfer", currency, amount, toAccount, "Success"));

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse("Source account or destination account not found"));
            }

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Invalid request"));
        }
    }

}
