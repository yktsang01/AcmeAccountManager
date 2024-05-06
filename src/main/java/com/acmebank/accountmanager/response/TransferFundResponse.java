/*
 * TransferFundResponse.java
 *
 * Acme Account Manager is the account manager of Acme Bank.
 *
 * This class or interface is part of the Acme Account Manager project.
 * The class or interface must not be used outside of this context.
 */
package com.acmebank.accountmanager.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * The transfer fund response.
 *
 * @param fromAccount         the source account
 * @param transactionDatetime the transaction datetime
 * @param type                the transaction type (transfer)
 * @param currency            the currency
 * @param amount              the amount
 * @param toAccount           the destination account
 * @param status              the status
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
public record TransferFundResponse(String fromAccount,
                                   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                                   LocalDateTime transactionDatetime,
                                   String type, String currency, BigDecimal amount,
                                   String toAccount, String status) {
}
