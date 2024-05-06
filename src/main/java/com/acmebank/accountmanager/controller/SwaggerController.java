/*
 * SwaggerController.java
 *
 * Acme Account Manager is the account manager of Acme Bank.
 *
 * This class or interface is part of the Acme Account Manager project.
 * The class or interface must not be used outside of this context.
 */
package com.acmebank.accountmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * The Swagger controller.
 *
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
@Controller
public class SwaggerController {

    /**
     * Shows the index page.
     *
     * @return the index page
     */
    @GetMapping("/")
    public ModelAndView index() {
        return apiIndex();
    }

    /**
     * Shows the Swagger UI.
     *
     * @return the Swagger UI
     */
    @GetMapping("/api")
    public ModelAndView apiIndex() {
        return new ModelAndView("redirect:/swagger-ui.html");
    }

}
