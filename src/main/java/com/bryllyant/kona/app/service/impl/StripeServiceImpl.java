/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.AppConfig;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.PaymentAccount;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AccountService;
import com.bryllyant.kona.app.service.AppConfigService;
import com.bryllyant.kona.app.service.KAbstractStripeService;
import com.bryllyant.kona.app.service.PaymentAccountService;
import com.bryllyant.kona.app.service.StripeService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.app.util.KCallback;
import com.bryllyant.kona.stripe.entity.KCard;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.MapConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service(StripeService.SERVICE_PATH)
public class StripeServiceImpl extends KAbstractStripeService<User,PaymentAccount> implements StripeService {

    private static Logger logger = LoggerFactory.getLogger(StripeServiceImpl.class);

    @Autowired
    private KConfig config;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PaymentAccountService paymentAccountService;

    @Autowired
    private AppConfigService appConfigService;

    @Autowired
    private SystemService system;



    @Override @SuppressWarnings("unchecked")
    protected UserService getUserService() {
        return userService;
    }



    @Override @SuppressWarnings("unchecked")
    protected PaymentAccountService getPaymentAccountService() {
        return paymentAccountService;
    }



    @Override
    protected String getStripeApiKey() {
        // check if there's a global key defined
        String key = config.getString("stripe.apiKey");

        if (key == null) {
            key = getConfig().getString("stripe.apiKey");
        }

        if (key == null) {
            throw new IllegalStateException("Stripe API Key not found");
        }

        return key;
    }



    @Override
    protected void sendPrimaryCardUpdateEmail(User user, KCard card) {
        Account account = accountService.fetchById(user.getAccountId());

        Map<String,Object> params = new HashMap<String,Object>();

        params.put("user", user);
        params.put("account", account);
        params.put("card", card);

        String templateName = "email.templates.sales.cardUpdate";

        String from = config.getString("system.mail.from");
        String to = user.getEmail();
        String replyTo = from;

        String subject = "Account Update";

        system.sendEmail(templateName, params, subject, from, replyTo, to, null, new KCallback<Email>() {
            @Override
            public void success(Email data) {

            }

            @Override
            public void error(Throwable t) {
                logger.error("Error sending email: " + t.getMessage(), t);
            }
        });


    }

    private Configuration getConfig() {

        Long appId = system.getSystemApp().getId();

        String _env = System.getProperty("env", "dev");

        AppConfig.Env  env = AppConfig.Env.valueOf(_env.toUpperCase());

        Map<String,Object> config = appConfigService.getConfig(appId, env);

        if (config == null) {
            throw new IllegalStateException("Configuration not found for appId: " + appId);
        }

        return new MapConfiguration(config);
    }

}
