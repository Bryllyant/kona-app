/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.SettingMapper;
import com.bryllyant.kona.app.entity.Setting;
import com.bryllyant.kona.app.entity.SettingExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.KAbstractSettingService;
import com.bryllyant.kona.app.service.SettingService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service(SettingService.SERVICE_PATH)
public class SettingServiceImpl 
		extends KAbstractSettingService<Setting,SettingExample,User> 
        implements SettingService {
    private static Logger logger = LoggerFactory.getLogger(SettingServiceImpl.class);

    @Autowired
    private SettingMapper settingDao;

    // ----------------------------------------------------------------------

	@Override
	protected Setting getNewObject() {
		return new Setting();
	}
	
    // ----------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected SettingMapper getDao() {
		return settingDao;
	}
	
    // ----------------------------------------------------------------------

	@Override
	protected SettingExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
			Map<String, Object> filter, boolean distinct) {
		
	   	SettingExample example = new SettingExample();

    	if (sortOrder != null) {
    		example.setOrderByClause(KMyBatisUtil.getOrderByString(sortOrder));
    	} else {
			example.setOrderByClause("created_date");
        }

    	if (startRow == null) startRow = 0;
    	if (resultSize == null) resultSize = 99999999;

        example.setOffset(startRow);
        example.setLimit(resultSize);
    	example.setDistinct(distinct);

    	KMyBatisUtil.buildExample(example.or().getClass(), example.or(), filter);
    	
    	return example;
	}

    // ----------------------------------------------------------------------

   
}
