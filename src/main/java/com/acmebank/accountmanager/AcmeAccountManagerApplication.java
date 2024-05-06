/*
 * AcmeAccountManagerApplication.java
 *
 * Acme Account Manager is the account manager of Acme Bank.
 *
 * This class or interface is part of the Acme Account Manager project.
 * The class or interface must not be used outside of this context.
 */
package com.acmebank.accountmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Provides the entry point to the application.
 *
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
@SpringBootApplication
public class AcmeAccountManagerApplication {

    /**
     * Provides the entry point to the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(AcmeAccountManagerApplication.class, args);
    }

}
