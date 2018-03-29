/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.dao.PreOrderMapper;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.PreOrder;
import com.bryllyant.kona.app.entity.PreOrderExample;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.KAbstractPreOrderService;
import com.bryllyant.kona.app.service.PreOrderService;
import com.bryllyant.kona.app.service.StripeService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.util.KCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service(PreOrderService.SERVICE_PATH)
public class PreOrderServiceImpl
        extends KAbstractPreOrderService<PreOrder, PreOrderExample, PreOrderMapper>
        implements PreOrderService {

    private static Logger logger = LoggerFactory.getLogger(PreOrderServiceImpl.class);

    @Autowired
    private PreOrderMapper preOrderMapper;

    @Autowired
    private StripeService stripeService;

    @Autowired
    private KConfig config;

    @Autowired
    private AppService appService;

    @Autowired
    private SystemService system;


    @Override @SuppressWarnings("unchecked")
    protected PreOrderMapper getMapper() {
        return preOrderMapper;
    }

    @Override @SuppressWarnings("unchecked")
    protected StripeService getStripeService() {
        return stripeService;
    }

    @Override
    protected PreOrderExample getEntityExampleObject() { return new PreOrderExample(); }


    protected void sendPreOrderReceipt(PreOrder preOrder) {
        App app = system.getSystemApp();

        String supportEmail = system.getConfig(app.getId()).getString("preOrder.support.email");
        String supportPhoneNumber = system.getConfig(app.getId()).getString("preOrder.support.phoneNumber");
        String supportPhoneNumberFormatted = system.getConfig(app.getId()).getString("preOrder.support.phoneNumberFormatted");

        String templateName = "email.templates.sales.preOrderReceipt";

        String from = config.getString("system.mail.from");
        String to = preOrder.getEmail();
        String replyTo = from;

        String subject = "Pre-Order";
        subject = "[" + app.getName() + "] " + subject;

        Map<String,Object> params = new HashMap<String,Object>();
        params.put("preOrder", preOrder);
        params.put("teamName", "The " + app.getName() + " Team");
        params.put("supportEmail", supportEmail);
        params.put("supportNumber", supportPhoneNumber);
        params.put("supportNumberFormatted", supportPhoneNumberFormatted);

        system.sendEmail(templateName, params, subject, from, replyTo, to, null, new KCallback<Email>() {
            @Override
            public void success(Email data) {

            }

            @Override
            public void error(Throwable t) {
                logger.error("Error sending email: " + t.getMessage(), t);
                system.alert("preOrderAlert: sendMail() Error", t);
            }
        });

    }

}
