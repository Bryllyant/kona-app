/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.dao.SalesLeadMapper;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.SalesLead;
import com.bryllyant.kona.app.entity.SalesLeadExample;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.KAbstractSalesLeadService;
import com.bryllyant.kona.app.service.KEmailException;
import com.bryllyant.kona.app.service.SalesLeadService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service(SalesLeadService.SERVICE_PATH)
public class SalesLeadServiceImpl 
		extends KAbstractSalesLeadService<SalesLead, SalesLeadExample, SalesLeadMapper>
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




    @Override @SuppressWarnings("unchecked")
    protected SalesLeadMapper getMapper() {
        return salesLeadMapper;
    }
    


    @Override
    protected SalesLeadExample getEntityExampleObject() { return new SalesLeadExample(); }

    


    protected void sendNotification(SalesLead lead) {

    	App app = null;

    	if (lead.getRefAppId() != null) {
    		app = appService.fetchById(lead.getRefAppId());
    	} else {
    		app = system.getSystemApp();
    	}


    	try {
    		String templateName = "email.templates.sales.salesLead";
            
    		String from = config.getString("system.mail.from");
    		String to = config.getString("sales.lead.to");
    		String replyTo = from;

    		String subject = "Sales Lead";
    		subject = "[" + app.getName() + "] " + subject;

    		Map<String,Object> params = new HashMap<String,Object>();
    		params.put("app", app);
    		params.put("salesLead", lead);

    		system.sendEmail(templateName, params, subject, from, replyTo, to, null);

    	} catch (KEmailException e) {
    		logger.error("Error sending email: " + e.getMessage(), e);
    		system.alert("salesAlert: sendMail() Error", e);
    	}
    }

}
