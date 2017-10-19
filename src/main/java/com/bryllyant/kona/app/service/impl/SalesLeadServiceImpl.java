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
		extends KAbstractSalesLeadService<SalesLead,SalesLeadExample> 
		implements SalesLeadService {
	
	private static Logger logger = LoggerFactory.getLogger(SalesLeadServiceImpl.class);
	   
    @Autowired
    private SalesLeadMapper salesLeadDao;
    
	@Autowired
	private KConfig config;
    
    @Autowired
    private AppService appService;
    
    @Autowired
    private SystemService system;


    // ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected SalesLeadMapper getDao() {
        return salesLeadDao;
    }
    
    // ----------------------------------------------------------------------------

    @Override
    protected SalesLeadExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
            Map<String, Object> filter, boolean distinct) {
    	SalesLeadExample example = new SalesLeadExample();

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
