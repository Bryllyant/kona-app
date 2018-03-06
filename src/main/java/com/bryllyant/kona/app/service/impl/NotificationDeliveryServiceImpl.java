/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.NotificationDeliveryMapper;
import com.bryllyant.kona.app.entity.NotificationDelivery;
import com.bryllyant.kona.app.entity.NotificationDeliveryExample;
import com.bryllyant.kona.app.service.KAbstractNotificationDeliveryService;
import com.bryllyant.kona.app.service.NotificationDeliveryService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(NotificationDeliveryService.SERVICE_PATH)
public class NotificationDeliveryServiceImpl 
		extends KAbstractNotificationDeliveryService<NotificationDelivery, NotificationDeliveryExample, NotificationDeliveryMapper>
		implements NotificationDeliveryService {
	
	private static Logger logger = LoggerFactory.getLogger(NotificationDeliveryServiceImpl.class);

	@Autowired
	private NotificationDeliveryMapper notificationDeliveryMapper;
    


    
	@Override @SuppressWarnings("unchecked")
	protected NotificationDeliveryMapper getMapper() {
		return notificationDeliveryMapper;
	}
	


	 @Override
    protected NotificationDeliveryExample getEntityExampleObject() { return new NotificationDeliveryExample(); }

	


}
