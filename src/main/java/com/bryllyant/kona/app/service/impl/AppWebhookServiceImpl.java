/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.AppWebhookMapper;
import com.bryllyant.kona.app.entity.AppWebhook;
import com.bryllyant.kona.app.entity.AppWebhookExample;
import com.bryllyant.kona.app.service.AppWebhookService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service(AppWebhookService.SERVICE_PATH)
public class AppWebhookServiceImpl 
		extends KAbstractService<AppWebhook, AppWebhookExample, AppWebhookMapper>
		implements AppWebhookService {
	
	private static Logger logger = LoggerFactory.getLogger(AppWebhookServiceImpl.class);

	@Autowired
	private AppWebhookMapper appWebhookMapper;
    
	@Override @SuppressWarnings("unchecked")
	protected AppWebhookMapper getMapper() {
		return appWebhookMapper;
	}


    @Override
    public void validate(AppWebhook appWebhook) {
        if (appWebhook.getCreatedDate() == null) {
            appWebhook.setCreatedDate(new Date());
        }

        if (appWebhook.getUid() == null) {
            appWebhook.setUid(uuid());
        }

        if (appWebhook.getName() == null) {
            appWebhook.setName(appWebhook.getUid());
        }

        appWebhook.setUpdatedDate(new Date());

        String slug = KInflector.getInstance().slug(appWebhook.getName());
        appWebhook.setSlug(slug);
    }

    @Override
    public AppWebhook fetchByAppIdAndSlug(Long appId, String slug) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
        filter.put("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


}
