/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.dao.SmsMapper;
import com.bryllyant.kona.app.entity.Sms;
import com.bryllyant.kona.app.entity.SmsExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.KAbstractSmsService;
import com.bryllyant.kona.app.service.SmsService;
import com.bryllyant.kona.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(SmsService.SERVICE_PATH)
public class SmsServiceImpl extends KAbstractSmsService<Sms, SmsExample, SmsMapper,User> implements SmsService {
    
    private static Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);
    
    @Autowired
    private KConfig config;
    
    @Autowired
    private SmsMapper smsMapper;

    @Autowired
    private UserService userService;
    


    @Override
    protected String getAccountSid() {
        String accountSid = config.getString("twilio.accountSid");
        return accountSid;
    }
    

    
    @Override
    protected String getAuthToken() {
        String authToken = config.getString("twilio.authToken");
        return authToken;
        
    }
    


    @Override
    public String getMessageStatusCallbackUrl() {
        return config.getString("twilio.messageStatusCallbackUrl");
    }
    


    @Override
    public String getFromPhoneNumber() {
        return config.getString("sms.fromPhoneNumber");
    }
    


    @Override
    public List<String> getTestPhoneNumberPrefixList() {
        List<String> list = new ArrayList<String>();
        
        String prefix = config.getString("sms.testPhoneNumberPrefix");
        
        if (prefix != null) {
            list.add(prefix);
        }
        
        return list;
    }
    


    @Override @SuppressWarnings("unchecked")
    protected SmsMapper getMapper() {
        return smsMapper;
    }
    


    @Override @SuppressWarnings("unchecked")
    protected UserService getUserService() {
        return userService;
    }



    @Override
    protected Sms getNewObject() {
        return new Sms();
    } 
    


    @Override
    protected SmsExample getEntityExampleObject() { return new SmsExample(); }



    
}
