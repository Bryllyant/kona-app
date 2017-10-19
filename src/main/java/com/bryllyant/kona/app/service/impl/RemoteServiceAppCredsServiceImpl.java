/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.RemoteServiceAppCredsMapper;
import com.bryllyant.kona.app.entity.RemoteServiceAppCreds;
import com.bryllyant.kona.app.entity.RemoteServiceAppCredsExample;
import com.bryllyant.kona.app.service.KAbstractRemoteServiceAppCredsService;
import com.bryllyant.kona.app.service.RemoteServiceAppCredsService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(RemoteServiceAppCredsService.SERVICE_PATH)
public class RemoteServiceAppCredsServiceImpl 
		extends KAbstractRemoteServiceAppCredsService<RemoteServiceAppCreds,RemoteServiceAppCredsExample> 
		implements RemoteServiceAppCredsService {
	
	private static Logger logger = LoggerFactory.getLogger(RemoteServiceAppCredsServiceImpl.class);

	@Autowired
	private RemoteServiceAppCredsMapper remoteServiceAppCredsDao;
    
	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected RemoteServiceAppCredsMapper getDao() {
		return remoteServiceAppCredsDao;
	}
    
	// ----------------------------------------------------------------------------

	@Override
	protected RemoteServiceAppCredsExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
			Map<String, Object> filter, boolean distinct) {
		RemoteServiceAppCredsExample example = new RemoteServiceAppCredsExample();

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
    
}
