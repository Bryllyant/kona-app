package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KNotificationDelivery;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;


public abstract class KAbstractNotificationDeliveryService<
            NOTIFICATION_DELIVERY extends KNotificationDelivery,
			NOTIFICATION_DELIVERY_EXAMPLE extends KEntityExample,
            MAPPER extends KMyBatisMapper<NOTIFICATION_DELIVERY, NOTIFICATION_DELIVERY_EXAMPLE>>
		extends KAbstractService<NOTIFICATION_DELIVERY,NOTIFICATION_DELIVERY_EXAMPLE,MAPPER>
		implements KNotificationDeliveryService<NOTIFICATION_DELIVERY> {
	
	private static Logger logger = LoggerFactory.getLogger(KAbstractNotificationDeliveryService.class);



	@Override
	public void validate(NOTIFICATION_DELIVERY notificationDelivery) {
		if (notificationDelivery.getCreatedDate() == null) {
			notificationDelivery.setCreatedDate(new Date());
		}

		notificationDelivery.setUpdatedDate(new Date());

		if (notificationDelivery.getCode() == null) {
			notificationDelivery.setCode(uuid());
		}
	}



	@Override
	public NOTIFICATION_DELIVERY fetchByCode(String code) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("code", code);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
    


	@Override
	public List<NOTIFICATION_DELIVERY> fetchByNotificationId(Long notificationId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("notificationId", notificationId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
}
