/*
 * SwaggerControllerTests.java
 *
 * Acme Account Manager is the account manager of Acme Bank.
 *
 * This class or interface is part of the Acme Account Manager project.
 * The class or interface must not be used outside of this context.
 */
package com.acmebank.accountmanager.test.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Provides the test cases for <code>SwaggerController</code>.
 *
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SwaggerControllerTests {

    /**
     * The mocked MVC.
     */
    @Autowired
    private MockMvc mvc;

    /**
     * Shows the index page.
     *
     * @throws Exception when it is unable to load the page
     */
    @Test
    public void index() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().is3xxRedirection());
    }

    /**
     * Shows the Swagger UI.
     *
     * @throws Exception when it is unable to load the page
     */
    @Test
    public void apiIndex() throws Exception {
        mvc.perform(get("/api"))
                .andExpect(status().is3xxRedirection());
    }

}
