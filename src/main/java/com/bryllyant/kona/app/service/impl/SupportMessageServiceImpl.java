/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.dao.SupportMessageMapper;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.SupportMessage;
import com.bryllyant.kona.app.entity.SupportMessageExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.KAbstractSupportMessageService;
import com.bryllyant.kona.app.service.KEmailException;
import com.bryllyant.kona.app.service.SupportMessageService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service(SupportMessageService.SERVICE_PATH)
public class SupportMessageServiceImpl 
		extends KAbstractSupportMessageService<SupportMessage,SupportMessageExample,User> 
		implements SupportMessageService {
	
	private static Logger logger = LoggerFactory.getLogger(SupportMessageServiceImpl.class);
	   
    @Autowired
    private SupportMessageMapper supportMessageDao;
    
	@Autowired
	private KConfig config;
    
    @Autowired
    private AppService appService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private SystemService system;


    // ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected SupportMessageMapper getDao() {
        return supportMessageDao;
    }
    

    @Override
    protected SupportMessage getNewObject() {
        return new SupportMessage();
    }
    
    // ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected UserService getUserService() {
        return userService;
    }

    // ----------------------------------------------------------------------------

    @Override
    protected SupportMessageExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
            Map<String, Object> filter, boolean distinct) {
    	SupportMessageExample example = new SupportMessageExample();

        if (sortOrder != null) {
            example.setOrderByClause(KMyBatisUtil.getOrderByString(sortOrder));
        }

        if (startRow == null) startRow = 0;
        if (resultSize == null) resultSize = 99999999;

        example.setOffset(startRow);
        example.setLimit(resultSize);
        example.setDistinct(distinct);

        KMyBatisUtil.buildExample(example.or().getClass(), example.or(), filter);
        return example;
    }
    
    // ----------------------------------------------------------------------------

    protected void sendNotification(SupportMessage message) {

    	App app = system.getSystemApp();
    	
    	if (message.getAppId() != null) {
    	    app = appService.fetchById(message.getAppId());
    	}


    	try {
    		String template = "email.templates.support.supportMessage";
            
    		String from = config.getString("system.mail.from");
    		String to = config.getString("system.mail.alertTo");
    		String replyTo = from;

    		String subject = "Support Message";
    		subject = "[" + app.getName() + "] " + subject;

    		Map<String,Object> params = new HashMap<String,Object>();
    		params.put("app", app);
    		params.put("message", message);

    		system.sendEmail(template, params, subject, from, replyTo, to, null);

    	} catch (KEmailException e) {
    		system.alert("salesAlert: sendMail() Error", e);
    	}
    }

}
