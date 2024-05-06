/*
 * AccountBalance.java
 *
 * Acme Account Manager is the account manager of Acme Bank.
 *
 * This class or interface is part of the Acme Account Manager project.
 * The class or interface must not be used outside of this context.
 */
package com.acmebank.accountmanager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

/**
 * The account balance. Represents the database table "account_balance".
 *
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
@Entity
@Table(name = "account_balance")
public class AccountBalance {

    /**
     * The customer ID.
     */
    @Id
    @Column(name = "customer_id")
    private String customerId;
    /**
     * The currency.
     */
    @Column(name = "currency")
    private String currency;
    /**
     * The balance.
     */
    @Column(name = "balance")
    private BigDecimal balance;

    /**
     * Constructs a <code>AccountBalance</code>.
     */
    public AccountBalance() {
    }

    /**
     * Constructs a <code>AccountBalance</code> with the customer ID, currency, balance.
     *
     * @param customerId the customer ID
     * @param currency   the currency
     * @param balance    the balance
     */
    public AccountBalance(String customerId, String currency, BigDecimal balance) {
        this.customerId = customerId;
        this.currency = currency;
        this.balance = balance;
    }

    /**
     * Returns the customer ID.
     *
     * @return the customer ID
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Returns the currency.
     *
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Returns the balance.
     *
     * @return the balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Assigns the balance.
     *
     * @param balance the balance
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
