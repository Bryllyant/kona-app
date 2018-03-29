/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.dao.SalesLeadMapper;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.CampaignAnalytics;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.SalesLead;
import com.bryllyant.kona.app.entity.SalesLeadExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.CampaignAnalyticsService;
import com.bryllyant.kona.app.service.KAbstractSalesLeadService;
import com.bryllyant.kona.app.service.SalesLeadService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.app.util.KCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service(SalesLeadService.SERVICE_PATH)
public class SalesLeadServiceImpl
        extends KAbstractSalesLeadService<
        SalesLead,
        SalesLeadExample,
        SalesLeadMapper,
        User,
        CampaignAnalytics>
        implements SalesLeadService {

    private static Logger logger = LoggerFactory.getLogger(SalesLeadServiceImpl.class);

    @Autowired
    private SalesLeadMapper salesLeadMapper;

    @Autowired
    private KConfig config;

    @Autowired
    private AppService appService;

    @Autowired
    private SystemService system;

    @Autowired
    private CampaignAnalyticsService campaignAnalyticsService;

    @Autowired
    private UserService userService;

    @Override @SuppressWarnings("unchecked")
    protected SalesLeadMapper getMapper() {
        return salesLeadMapper;
    }

    @Override @SuppressWarnings("unchecked")
    protected CampaignAnalyticsService getCampaignAnalyticsService() {
        return campaignAnalyticsService;
    }

    @Override @SuppressWarnings("unchecked")
    protected UserService getUserService() {
        return userService;
    }


    protected void sendNotification(SalesLead lead) {
        App app = system.getSystemApp();

        String templateName = "email.templates.sales.salesLead";

        String from = config.getString("system.mail.from");
        String to = config.getString("system.mail.salesTo");
        String replyTo = from;

        String subject = "Sales Lead";
        subject = "[" + app.getName() + "] " + subject;

        Map<String,Object> params = new HashMap<>();
        params.put("app", app);
        params.put("salesLead", lead);

        system.sendEmail(templateName, params, subject, from, replyTo, to, null, new KCallback<Email>() {
            @Override
            public void success(Email data) {

            }

            @Override
            public void error(Throwable t) {
                logger.error("Error sending email: " + t.getMessage(), t);
                system.alert("salesAlert: sendMail() Error", t);
            }
        });
    }

}
