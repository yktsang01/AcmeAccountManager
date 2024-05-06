/*
 * TransferTransactionRepositoryTests.java
 *
 * Acme Account Manager is the account manager of Acme Bank.
 *
 * This class or interface is part of the Acme Account Manager project.
 * The class or interface must not be used outside of this context.
 */
package com.acmebank.accountmanager.test.entity;

import com.acmebank.accountmanager.entity.TransferTransaction;
import com.acmebank.accountmanager.entity.TransferTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Provides the test cases for <code>TransferTransactionRepository</code>.
 *
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
@SpringBootTest
public class TransferTransactionRepositoryTests {

    /**
     * The mocked transfer transaction repository.
     */
    @MockBean
    private TransferTransactionRepository transferTxnRepo;

    /**
     * Initializes the mocked data.
     */
    @BeforeEach
    public void init() {
        TransferTransaction dummyTxn =
                new TransferTransaction("87654321", "ABC",
                        BigDecimal.TEN, "23456789");

        when(transferTxnRepo.save(any(TransferTransaction.class)))
                .thenReturn(dummyTxn);
    }

    /**
     * Tests saving transfer transaction.
     */
    @Test
    public void save() {
        TransferTransaction savedTxn = transferTxnRepo.save(
                new TransferTransaction("87654321", "ABC",
                        BigDecimal.TEN, "23456789"));
        assertEquals("87654321", savedTxn.getSourceCustomerId());
    }

}
