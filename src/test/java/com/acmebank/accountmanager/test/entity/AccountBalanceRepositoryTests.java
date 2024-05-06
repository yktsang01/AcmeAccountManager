/*
 * AccountBalanceRepositoryTests.java
 *
 * Acme Account Manager is the account manager of Acme Bank.
 *
 * This class or interface is part of the Acme Account Manager project.
 * The class or interface must not be used outside of this context.
 */
package com.acmebank.accountmanager.test.entity;

import com.acmebank.accountmanager.entity.AccountBalance;
import com.acmebank.accountmanager.entity.AccountBalanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Provides the test cases for <code>AccountBalanceRepository</code>.
 *
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
@SpringBootTest
public class AccountBalanceRepositoryTests {

    /**
     * The mocked account balance repository.
     */
    @MockBean
    private AccountBalanceRepository acctBalRepo;

    /**
     * Initializes the mocked data.
     */
    @BeforeEach
    public void init() {
        AccountBalance dummyBalance =
                new AccountBalance("87654321", "XXX", BigDecimal.TEN);

        when(acctBalRepo.save(any(AccountBalance.class)))
                .thenReturn(dummyBalance);
        when(acctBalRepo.findById(anyString()))
                .thenReturn(Optional.of(dummyBalance));
    }

    /**
     * Tests saving account balance.
     */
    @Test
    public void save() {
        AccountBalance savedBalance = acctBalRepo.save(
                new AccountBalance("87654321", "ABC", BigDecimal.TEN));
        assertEquals("87654321", savedBalance.getCustomerId());
    }

    /**
     * Tests finding account balance by ID.
     */
    @Test
    public void findById() {
        Optional<AccountBalance> balOpt = acctBalRepo.findById("87654321");
        assertTrue(balOpt.isPresent());
    }

}
