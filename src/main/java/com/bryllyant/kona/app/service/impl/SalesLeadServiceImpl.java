/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.SalesLeadMapper;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignAnalytics;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.SalesLead;
import com.bryllyant.kona.app.entity.SalesLeadExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.CampaignAnalyticsService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.SalesLeadService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.remote.service.KServiceClient;
import com.bryllyant.kona.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(SalesLeadService.SERVICE_PATH)
public class SalesLeadServiceImpl
        extends KAbstractService<SalesLead,SalesLeadExample,SalesLeadMapper>
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

        system.sendEmail(templateName, params, subject, from, replyTo, to, null, new Callback<Email>() {
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


    @Override
    public void validate(SalesLead salesLead) {
        if (salesLead.getCreatedDate() == null) {
            salesLead.setCreatedDate(new Date());
        }

        salesLead.setUpdatedDate(new Date());
        
        if (salesLead.getUid() == null) {
            salesLead.setUid(uuid());
        }
    }


    @Override @Transactional
    public SalesLead create(SalesLead salesLead, KServiceClient client) {
        // TODO: validate email and mobileNumber

        if (salesLead.getUserId() == null) {
            User user = null;

            if (salesLead.getEmail() != null) {
                user = userService.fetchByEmail(salesLead.getEmail());
            }

            if (user == null && salesLead.getMobileNumber() != null) {
                user = userService.fetchByMobileNumber(salesLead.getMobileNumber());
            }

            if (user != null) {
                salesLead.setUserId(user.getId());
            }
        }

        String action = "subscribe";
        String category = Campaign.Goal.LEAD_GEN.name().toLowerCase();
        String label = null;
        Double value = null;

        CampaignAnalytics analytics = campaignAnalyticsService.create(
                client,
                salesLead.getTargetId(),
                action,
                category,
                label,
                value,
                salesLead.isCampaignConversion(),
                salesLead.getUserId(),
                salesLead.getEmail(),
                salesLead.getMobileNumber()
        );

        salesLead.setAnalyticsId(analytics.getId());

        salesLead = add(salesLead);

        analytics.setLabel(salesLead.getUid());
        campaignAnalyticsService.save(analytics);

        sendNotification(salesLead);

        return salesLead;
    }



    @Override
    public List<SalesLead> fetchByReferredById(Long referredById) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("referredById", referredById);
        return fetchByCriteria(filter);
    }

    @Override
    public List<SalesLead> fetchByCampaignId(Long campaignId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("campaignId", campaignId);
        return fetchByCriteria(filter);
    }


}
