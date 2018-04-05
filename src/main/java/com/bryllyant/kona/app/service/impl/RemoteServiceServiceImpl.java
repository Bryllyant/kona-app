/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.RemoteServiceMapper;
import com.bryllyant.kona.app.entity.RemoteService;
import com.bryllyant.kona.app.entity.RemoteServiceExample;
import com.bryllyant.kona.app.service.RemoteServiceService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service(RemoteServiceService.SERVICE_PATH)
public class RemoteServiceServiceImpl 
		extends KAbstractService<RemoteService, RemoteServiceExample, RemoteServiceMapper>
        implements RemoteServiceService, com.bryllyant.kona.remote.service.KService, com.bryllyant.kona.data.service.KEntityService<RemoteService> {
	
	private static Logger logger = LoggerFactory.getLogger(RemoteServiceServiceImpl.class);

	@Autowired
	private RemoteServiceMapper remoteServiceMapper;
    
	@Override @SuppressWarnings("unchecked")
	protected RemoteServiceMapper getMapper() {
		return remoteServiceMapper;
	}
    
    @Override
    public void validate(RemoteService remoteService) {
        if (remoteService.getCreatedDate() == null) {
            remoteService.setCreatedDate(new Date());
        }

        remoteService.setUpdatedDate(new Date());

        if (remoteService.getName() != null) {
            String slug = KInflector.getInstance().slug(remoteService.getName());
            remoteService.setSlug(slug);
        }

        if (remoteService.getUid() == null) {
            remoteService.setUid(uuid());
        }
    }

    @Override
    public RemoteService fetchBySlug(String slug) {
        slug = slug.trim().toLowerCase();
        Map<String,Object> filter = KMyBatisUtil.createFilter("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0,9999, null, filter,  false));
    }
}
