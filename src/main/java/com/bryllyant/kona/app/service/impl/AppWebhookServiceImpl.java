/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.AppWebhookMapper;
import com.bryllyant.kona.app.entity.AppWebhook;
import com.bryllyant.kona.app.entity.AppWebhookExample;
import com.bryllyant.kona.app.service.AppWebhookService;
import com.bryllyant.kona.app.service.KAbstractAppWebhookService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(AppWebhookService.SERVICE_PATH)
public class AppWebhookServiceImpl 
		extends KAbstractAppWebhookService<AppWebhook, AppWebhookExample, AppWebhookMapper>
		implements AppWebhookService {
	
	private static Logger logger = LoggerFactory.getLogger(AppWebhookServiceImpl.class);

	@Autowired
	private AppWebhookMapper appWebhookMapper;
    


	@Override @SuppressWarnings("unchecked")
	protected AppWebhookMapper getMapper() {
		return appWebhookMapper;
	}
    

	
	 @Override
    protected AppWebhookExample getEntityExampleObject() { return new AppWebhookExample(); }




}
