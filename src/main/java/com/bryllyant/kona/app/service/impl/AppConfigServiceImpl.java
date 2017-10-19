/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.AppConfigMapper;
import com.bryllyant.kona.app.entity.AppConfig;
import com.bryllyant.kona.app.entity.AppConfigExample;
import com.bryllyant.kona.app.service.AppConfigService;
import com.bryllyant.kona.app.service.KAbstractAppConfigService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(AppConfigService.SERVICE_PATH)
public class AppConfigServiceImpl 
		extends KAbstractAppConfigService<AppConfig,AppConfigExample> 
		implements AppConfigService {
	
	private static Logger logger = LoggerFactory.getLogger(AppConfigServiceImpl.class);

	@Autowired
	private AppConfigMapper appConfigDao;
    
	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected AppConfigMapper getDao() {
		return appConfigDao;
	}
    
	// ----------------------------------------------------------------------------
	
	@Override
	protected AppConfigExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
			Map<String, Object> filter, boolean distinct) {
		AppConfigExample example = new AppConfigExample();

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

}
