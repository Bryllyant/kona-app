/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.PaymentAccount;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AccountService;
import com.bryllyant.kona.app.service.AppConfigService;
import com.bryllyant.kona.app.service.KAbstractStripeService;
import com.bryllyant.kona.app.service.KEmailException;
import com.bryllyant.kona.app.service.PaymentAccountService;
import com.bryllyant.kona.app.service.StripeService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
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

	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected UserService getUserService() {
		return userService;
	}
	
	// ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected PaymentAccountService getPaymentAccountService() {
        return paymentAccountService;
    }	

	// ----------------------------------------------------------------------------

	@Override
	protected String getStripeApiKey(Long appId) {
		
		if (appId == null) {
			throw new IllegalArgumentException("getStripeApiKey: appId is null");
		}
		
		// check if there's a global key defined
		String key = config.getString("stripe.apiKey");

		if (key == null) {
			key = getConfig(appId).getString("stripe.apiKey");
		}
		
		if (key == null) {
			throw new IllegalStateException("Stripe API Key not found for appId: " + appId);
		}

		return key;
	}
	
	// ----------------------------------------------------------------------------

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

	    try {
	        system.sendEmail(templateName, params, subject, from, replyTo, to, null);
	    } catch (KEmailException e) {
	        logger.error("Error sending email: " + e.getMessage(), e);
	    }

	}

	// ----------------------------------------------------------------------------
	
	private Configuration getConfig(Long appId) {
		String env = System.getProperty("env", "dev");
		Map<String,Object> config = appConfigService.getConfig(appId, env);
		if (config == null) {
			throw new IllegalStateException("Cofiguration not found for appId: " + appId);
		}

		return new MapConfiguration(config);
	}

}
