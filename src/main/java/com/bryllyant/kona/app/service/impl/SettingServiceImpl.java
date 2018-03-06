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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(SettingService.SERVICE_PATH)
public class SettingServiceImpl 
		extends KAbstractSettingService<Setting, SettingExample, SettingMapper,User>
        implements SettingService {
    private static Logger logger = LoggerFactory.getLogger(SettingServiceImpl.class);

    @Autowired
    private SettingMapper settingMapper;



	@Override
	protected Setting getNewObject() {
		return new Setting();
	}
	


	@Override @SuppressWarnings("unchecked")
	protected SettingMapper getMapper() {
		return settingMapper;
	}
	


	 @Override
    protected SettingExample getEntityExampleObject() { return new SettingExample(); }




   
}
