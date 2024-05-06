/*
 * TransferTransactionRepository.java
 *
 * Acme Account Manager is the account manager of Acme Bank.
 *
 * This class or interface is part of the Acme Account Manager project.
 * The class or interface must not be used outside of this context.
 */
package com.acmebank.accountmanager.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * Performs the database operations of the <code>TransferTransaction</code>.
 * Primary key to the database table "transfer_transaction" is the transfer transaction ID.
 *
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
@Repository
public interface TransferTransactionRepository
        extends CrudRepository<TransferTransaction, BigInteger> {
}
