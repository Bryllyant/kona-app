/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.AppConfig;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.PaymentAccount;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AccountService;
import com.bryllyant.kona.app.service.AppConfigService;
import com.bryllyant.kona.app.service.PaymentAccountService;
import com.bryllyant.kona.app.service.StripeService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.stripe.entity.KCard;
import com.bryllyant.kona.stripe.entity.KCharge;
import com.bryllyant.kona.stripe.entity.KCustomer;
import com.bryllyant.kona.stripe.entity.KStripeException;
import com.bryllyant.kona.util.Callback;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.MapConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service(StripeService.SERVICE_PATH)
public class StripeServiceImpl extends com.bryllyant.kona.stripe.service.KAbstractStripeService implements StripeService {

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

        system.sendEmail(templateName, params, subject, from, replyTo, to, null, new Callback<Email>() {
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



    private String getStripeUid(User user) {
        String stripeUid = null;

        PaymentAccount paymentAccount = paymentAccountService.fetchDefault(user.getAccountId());

        if (paymentAccount != null) {
            stripeUid = paymentAccount.getProviderCustomerId();
        }

        return stripeUid;
    }

    private String getUserDescription(User user) {
        String s = "Username: " + user.getUsername();
		/*
        s += "\nFirst Name: " + user.getFirstName();
        s += "\nLast Name: " + user.getLastName();
        s += "\nCompany: " + user.getCompany();
        s += "\nEmail: " + user.getEmail();
		 */
        return s;
    }


    @Override
    public String addCustomer(Long userId) {
        User user = userService.fetchById(userId);

        KCustomer customer = addCustomer(user.getEmail(), getUserDescription(user));

        return customer.getId();
    }

    @Override
    public void deleteCustomer(Long userId) {
        User user = userService.fetchById(userId);
        deleteCustomer(getStripeUid(user));
    }



    @Override
    public void updateCustomer(Long userId) {
        User user = userService.fetchById(userId);

        String stripeUid = getStripeUid(user);

        if (stripeUid != null) {
            KCustomer customer = fetchCustomerById(stripeUid);

            customer.setEmail(user.getEmail());

            customer.setDescription(getUserDescription(user));

            updateCustomer(customer);
        }
    }



    @Override
    public String updateStripeUid(Long userId, String cardToken)  {
        User user = userService.fetchById(userId);

        String email = user.getEmail();

        String description = user.getUsername();

        String stripeUid = getStripeUid(user);

        if (stripeUid == null) {
            KCustomer customer = addCustomer(email, description, cardToken);

            stripeUid = customer.getId();

            KCard card = customer.getDefaultCard();

            paymentAccountService.addStripeAccount(user, stripeUid, card.getLast4(), true);

        } else {
            addPrimaryCard(userId, cardToken);
        }

        return stripeUid;
    }



    @Override
    public KCharge chargeCustomer(Long userId, BigDecimal amount,
                                  String description, String receiptEmail, Map<String,Object> metadata,
                                  Map<String,Object> shipping) throws KStripeException {
        logger.debug("calling chargeCustomer");
        User user = userService.fetchById(userId);

        String stripeUid = getStripeUid(user);

        if (stripeUid == null) {
            String s = "Stripe UID is null for user: " + user;
            throw new KStripeException(s);
        }

        return chargeCustomer(stripeUid, amount, description, receiptEmail, metadata, shipping);
    }




    @Override
    public KCard addPrimaryCard(Long userId, KCard card) {
        User user = userService.fetchById(userId);

        String stripeUid = getStripeUid(user);

        return addCustomerCard(stripeUid, card);
    }



    /*
     * Passing source will create a new source object, make it the new customer default source, and delete
     * the old customer default if one exists. If you want to add additional sources instead of replacing
     * the existing default, use the card creation API. Whenever you attach a card to a customer, Stripe
     * will automatically validate the card.
     *
     * https://stripe.com/docs/api/java#update_customer
     */
    @Override
    public KCard addPrimaryCard(Long userId, String cardToken) {
        User user = userService.fetchById(userId);

        String stripeUid = getStripeUid(user);

        return addCustomerCard(stripeUid, cardToken);
    }



    @Override
    public KCard updatePrimaryCard(Long userId, KCard card) {
        card = addPrimaryCard(userId, card);
        User user = userService.fetchById(userId);
        sendPrimaryCardUpdateEmail(user, card);
        return card;
    }



    @Override
    public KCard updatePrimaryCard(Long userId, String cardToken) {
        KCard card = addPrimaryCard(userId, cardToken);
        User user = userService.fetchById(userId);
        sendPrimaryCardUpdateEmail(user, card);
        return card;
    }



    @Override
    public KCard getPrimaryCard(Long userId) {
        User user = userService.fetchById(userId);

        String stripeUid = getStripeUid(user);

        if (stripeUid == null) {
            logger.warn("getPrimaryCard: stripeUid is null");
            return null;
        }

        return fetchCustomerActiveCard(stripeUid);
    }



    @Override
    public String getPrimaryCardLast4(Long userId) {

        if (userId == null) {
            logger.warn("getPrimaryCardLast4ByUserId: userId is null");
            return null;
        }

        KCard card = getPrimaryCard(userId);

        if (card == null) {
            logger.debug("getPrimaryCardLast4ByUserId: card is null");
            return null;
        }

        return card.getLast4();

    }



}
