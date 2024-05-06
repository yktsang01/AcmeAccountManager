/*
 * TransferTransaction.java
 *
 * Acme Account Manager is the account manager of Acme Bank.
 *
 * This class or interface is part of the Acme Account Manager project.
 * The class or interface must not be used outside of this context.
 */
package com.acmebank.accountmanager.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * The transfer transaction. Represents the database table "transfer_transaction".
 *
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
@Entity
@Table(name = "transfer_transaction")
public class TransferTransaction {

    /**
     * The transfer transaction ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_transaction_id")
    private BigInteger transferTransactionId;
    /**
     * The transaction datetime.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "transaction_datetime")
    private LocalDateTime transactionDateTime;
    /**
     * The source customer ID.
     */
    @Column(name = "source_customer_id")
    private String sourceCustomerId;
    /**
     * The currency.
     */
    @Column(name = "currency")
    private String currency;
    /**
     * The transfer amount.
     */
    @Column(name = "transfer_amount")
    private BigDecimal transferAmount;
    /**
     * The destination customer ID.
     */
    @Column(name = "destination_customer_id")
    private String destinationCustomerId;

    /**
     * Constructs a <code>TransferTransaction</code>.
     */
    public TransferTransaction() {
    }

    /**
     * Constructs a <code>TransferTransaction</code> with source customer ID,
     * currency, transfer amount, destination customer ID.
     *
     * @param sourceCustomerId      the source customer ID
     * @param currency              the currency
     * @param transferAmount        the transfer amount
     * @param destinationCustomerId the destination customer ID
     */
    public TransferTransaction(String sourceCustomerId,
                               String currency, BigDecimal transferAmount, String destinationCustomerId) {
        this.transactionDateTime = LocalDateTime.now();
        this.sourceCustomerId = sourceCustomerId;
        this.currency = currency;
        this.transferAmount = transferAmount;
        this.destinationCustomerId = destinationCustomerId;
    }

    /**
     * Returns the transfer transaction ID.
     *
     * @return the transfer transaction ID
     */
    public BigInteger getTransferTransactionId() {
        return transferTransactionId;
    }

    /**
     * Returns the transaction datetime.
     *
     * @return the transaction datetime
     */
    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    /**
     * Returns the source customer ID.
     *
     * @return the source customer ID
     */
    public String getSourceCustomerId() {
        return sourceCustomerId;
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
     * Returns the transfer amount;
     *
     * @return the transfer amount
     */
    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    /**
     * Returns the destination customer ID.
     *
     * @return the destination customer ID
     */
    public String getDestinationCustomerId() {
        return destinationCustomerId;
    }

}
