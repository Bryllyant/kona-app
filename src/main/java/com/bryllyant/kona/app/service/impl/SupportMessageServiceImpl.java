/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.SupportMessageMapper;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.SupportMessage;
import com.bryllyant.kona.app.entity.SupportMessageExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.SupportMessageService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.remote.service.KServiceClient;
import com.bryllyant.kona.util.Callback;
import com.bryllyant.kona.util.KNameParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(SupportMessageService.SERVICE_PATH)
public class SupportMessageServiceImpl
        extends KAbstractService<SupportMessage,SupportMessageExample,SupportMessageMapper>
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

        system.sendEmail(template, params, subject, from, replyTo, to, null, new Callback<Email>() {
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
    @Override
    public void validate(SupportMessage supportMessage) {
        if (supportMessage.getCreatedDate() == null) {
            supportMessage.setCreatedDate(new Date());
        }

        if (supportMessage.getUid() == null) {
            supportMessage.setUid(uuid());
        }

        supportMessage.setUpdatedDate(new Date());

        if (supportMessage.getUserId() == null && supportMessage.getEmail() != null) {
            User user = userService.fetchByEmail(supportMessage.getEmail());

            if (user != null) {
                supportMessage.setUserId(user.getId());
            }
        }

        if (supportMessage.getUserId() == null && supportMessage.getMobileNumber() != null) {
            User user = userService.fetchByMobileNumber(supportMessage.getMobileNumber());

            if (user != null) {
                supportMessage.setUserId(user.getId());
            }
        }

        if (supportMessage.getUserId() != null
                && (supportMessage.getFirstName() == null || supportMessage.getLastName() == null)) {

            User user = userService.fetchById(supportMessage.getUserId());

            if (supportMessage.getFirstName() == null) {
                supportMessage.setFirstName(user.getFirstName());
            }

            if (supportMessage.getLastName() == null) {
                supportMessage.setLastName(user.getLastName());
            }
        }
    }




    @Override
    public List<SupportMessage> fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    @Override
    public List<SupportMessage> fetchByEmail(String email) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("email", email);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    @Override
    public List<SupportMessage> fetchByMobileNumber(String mobileNumber) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("mobileNumber", mobileNumber);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    @Override
    public SupportMessage send(SupportMessage message) {
        message = save(message);
        sendNotification(message);
        return message;
    }



    @Override
    public SupportMessage send(KServiceClient client, String name, String email, String mobileNumber, String message) {
        SupportMessage supportMessage = new SupportMessage();

        String firstName = null;
        String lastName = null;

        if (name != null) {
            KNameParser parser = KNameParser.parse(name, true);
            firstName = parser.getFirstName();
            lastName = parser.getLastName();
        }

        supportMessage.setUserId(client.getUserId());
        supportMessage.setFirstName(firstName);
        supportMessage.setLastName(lastName);
        supportMessage.setEmail(email);
        supportMessage.setMobileNumber(mobileNumber);
        supportMessage.setMessage(message);
        supportMessage.setHostname(client.getHostname());
        supportMessage.setUserAgent(client.getUserAgent());

        return send(supportMessage);
    }
}
