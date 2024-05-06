/*
 * AccountBalanceResponse.java
 *
 * Acme Account Manager is the account manager of Acme Bank.
 *
 * This class or interface is part of the Acme Account Manager project.
 * The class or interface must not be used outside of this context.
 */
package com.acmebank.accountmanager.response;

import java.math.BigDecimal;

/**
 * The account balance response.
 *
 * @param currency the currency
 * @param balance the balance
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
public record AccountBalanceResponse(String currency, BigDecimal balance) {
}
