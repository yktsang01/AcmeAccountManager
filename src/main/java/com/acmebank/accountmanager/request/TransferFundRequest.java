/*
 * TransferFundRequest.java
 *
 * Acme Account Manager is the account manager of Acme Bank.
 *
 * This class or interface is part of the Acme Account Manager project.
 * The class or interface must not be used outside of this context.
 */
package com.acmebank.accountmanager.request;

import java.math.BigDecimal;

/**
 * The transfer fund request.
 *
 * @param sourceCustomerId      the source customer ID
 * @param destinationCustomerId the destination customer ID
 * @param transferCurrency      the transfer currency
 * @param transferAmount        the transfer amount
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
public record TransferFundRequest(String sourceCustomerId,
                                  String destinationCustomerId,
                                  String transferCurrency,
                                  BigDecimal transferAmount) {
}
