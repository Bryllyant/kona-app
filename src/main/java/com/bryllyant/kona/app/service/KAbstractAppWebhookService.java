/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KAppWebhook;
import com.bryllyant.kona.app.util.KUtil;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

public abstract class KAbstractAppWebhookService<A extends KAppWebhook,EXAMPLE extends KEntityExample, MAPPER extends KMyBatisMapper<A, EXAMPLE>> extends KAbstractService<A,EXAMPLE,MAPPER>
implements KAppWebhookService<A> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractAppWebhookService.class);



	protected String generateUid() {
		return KUtil.uuid();
	}



	@Override 
	public void validate(A appWebhook) {
		if (appWebhook.getCreatedDate() == null) {
			appWebhook.setCreatedDate(new Date());
		}

		if (appWebhook.getUid() == null) {
			appWebhook.setUid(generateUid());
		}

		if (appWebhook.getName() == null) {
			appWebhook.setName(appWebhook.getUid());
		}
		
		appWebhook.setUpdatedDate(new Date());

		String slug = KInflector.getInstance().slug(appWebhook.getName());
		appWebhook.setSlug(slug);
	}


    
	@Override
	public A fetchByUid(String uid) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
    


	@Override
	public A fetchByAppIdAndSlug(Long appId, String slug) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
        filter.put("slug", slug);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}



}
