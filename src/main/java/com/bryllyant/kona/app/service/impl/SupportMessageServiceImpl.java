/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.app.dao.SupportMessageMapper;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.SupportMessage;
import com.bryllyant.kona.app.entity.SupportMessageExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.KAbstractSupportMessageService;
import com.bryllyant.kona.app.service.SupportMessageService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.app.util.KCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service(SupportMessageService.SERVICE_PATH)
public class SupportMessageServiceImpl
        extends KAbstractSupportMessageService<SupportMessage, SupportMessageExample, SupportMessageMapper,User>
        implements SupportMessageService {

    private static Logger logger = LoggerFactory.getLogger(SupportMessageServiceImpl.class);

    @Autowired
    private SupportMessageMapper supportMessageMapper;

    @Autowired
    private KConfig config;

    @Autowired
    private AppService appService;

    @Autowired
    private UserService userService;

    @Autowired
    private SystemService system;

    @Override @SuppressWarnings("unchecked")
    protected SupportMessageMapper getMapper() {
        return supportMessageMapper;
    }

    @Override
    protected SupportMessage getNewObject() {
        return new SupportMessage();
    }


    @Override @SuppressWarnings("unchecked")
    protected UserService getUserService() {
        return userService;
    }

    @Override
    protected SupportMessageExample getEntityExampleObject() { return new SupportMessageExample(); }

    protected void sendNotification(SupportMessage message) {
        App app = system.getSystemApp();

        String template = "email.templates.support.supportMessage";

        String from = config.getString("system.mail.from");
        String to = config.getString("system.mail.alertTo");
        String replyTo = from;

        String subject = "Support Message";
        subject = "[" + app.getName() + "] " + subject;

        Map<String,Object> params = new HashMap<String,Object>();
        params.put("app", app);
        params.put("message", message);

        system.sendEmail(template, params, subject, from, replyTo, to, null, new KCallback<Email>() {
            @Override
            public void success(Email data) {

            }

            @Override
            public void error(Throwable t) {
                logger.error(t.getMessage(), t);
                system.alert("salesAlert: sendMail() Error", t);
            }
        });


    }

}
