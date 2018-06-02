/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PreOrderMapper;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.PreOrder;
import com.bryllyant.kona.app.entity.PreOrderExample;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.PreOrderService;
import com.bryllyant.kona.app.service.StripeService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.stripe.entity.KCharge;
import com.bryllyant.kona.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(PreOrderService.SERVICE_PATH)
public class PreOrderServiceImpl
        extends KAbstractService<PreOrder, PreOrderExample, PreOrderMapper>
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

        system.sendEmail(templateName, params, subject, from, replyTo, to, null, new Callback<Email>() {
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
    @Override
    public void validate(PreOrder preOrder) {
        if (preOrder.getCreatedDate() == null) {
            preOrder.setCreatedDate(new Date());
        }

        if (preOrder.getUid() == null) {
            preOrder.setUid(uuid());
        }
    }



    @Override
    public List<PreOrder> fetchByAppId(Long appId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
        return fetchByCriteria(filter);
    }


    @Override
    public PreOrder create(PreOrder preOrder, Map<String,Object> metadata, boolean processPayment, boolean sendReceipt) {
        if (processPayment) {
            preOrder = processPayment(preOrder, metadata);
        }

        preOrder = add(preOrder);

        if (sendReceipt) {
            if (preOrder.getEmail() != null) {
                logger.debug("sending payment receipt ...");
                sendPreOrderReceipt(preOrder);
            }
        }

        return preOrder;
    }



    private PreOrder processPayment(PreOrder preOrder, Map<String,Object> metadata) {
        if (preOrder.getProcessor() == null) {
            throw new IllegalArgumentException("processor must be specified");
        }

        if (!preOrder.getProcessor().equalsIgnoreCase("stripe")) {
            throw new IllegalArgumentException("Only processor 'stripe' is currently supported");
        }

        if (preOrder.getPaymentToken() == null) {
            throw new IllegalArgumentException("paymentToken must be specified");
        }

        if (preOrder.getAmount() == null) {
            throw new IllegalArgumentException("amount must be specified");
        }

        KCharge charge = stripeService.chargeCard(preOrder.getPaymentToken(), preOrder.getAmount(),
                preOrder.getPaymentDescription(), null, metadata, null);

        if (charge != null) {
            preOrder.setPaymentRef(charge.getId());
            if (charge.getCard() != null) {
                preOrder.setPaymentCardLast4(charge.getCard().getLast4());
            }
        }

        return preOrder;
    }
}
