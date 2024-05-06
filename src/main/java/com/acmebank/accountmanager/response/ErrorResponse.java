/*
 * ErrorResponse.java
 *
 * Acme Account Manager is the account manager of Acme Bank.
 *
 * This class or interface is part of the Acme Account Manager project.
 * The class or interface must not be used outside of this context.
 */
package com.acmebank.accountmanager.response;

/**
 * The error response.
 * Used whenever the intended API response fails.
 *
 * @param errorMessage the error message
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
public record ErrorResponse(String errorMessage) {
}
