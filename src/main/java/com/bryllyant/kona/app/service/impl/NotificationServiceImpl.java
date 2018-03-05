package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.dao.NotificationMapper;
import com.bryllyant.kona.app.entity.KNotificationChannel;
import com.bryllyant.kona.app.entity.Notification;
import com.bryllyant.kona.app.entity.NotificationDelivery;
import com.bryllyant.kona.app.entity.NotificationExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.KAbstractNotificationService;
import com.bryllyant.kona.app.service.NotificationDeliveryService;
import com.bryllyant.kona.app.service.NotificationService;
import com.bryllyant.kona.app.service.SettingService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import com.bryllyant.kona.util.KJsonUtil;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.MapConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(NotificationService.SERVICE_PATH)
public class NotificationServiceImpl 
		extends KAbstractNotificationService<Notification,NotificationExample,NotificationDelivery,User>
		implements NotificationService {
	
	private static Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);
	


	@Autowired
	private KConfig config;
	
	@Autowired
	private NotificationMapper notificationDao;
	
	@Autowired
	private NotificationDeliveryService notificationDeliveryService;
	
	@Autowired
	private SettingService settingService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private SystemService system;
	
	private Long appId = null;
	


	@Override @SuppressWarnings("unchecked")
	protected NotificationMapper getDao() {
		return notificationDao;
	}



	@Override
	protected boolean entityHasBlobs() {
		return true;
	}
    

	
	@Override
    protected Notification getNewNotificationObject() {
    	return new Notification();
    }


    
	@Override
    protected NotificationDelivery getNewNotificationDeliveryObject() {
    	return new NotificationDelivery();
    }



	@Override @SuppressWarnings("unchecked")
	protected UserService getUserService() {
		return userService;
	}



	@Override @SuppressWarnings("unchecked")
	protected NotificationDeliveryService getNotificationDeliveryService() {
		return notificationDeliveryService;
	}




	protected Configuration getUserConfig(User user) {
		return new MapConfiguration(settingService.getUserSettings(user));
	}
	


	protected User getUser(Long userId) {
		return userService.fetchById(userId);
	}

	

	
	protected Long getAppId() {
		if (appId == null) {
			appId = system.getSystemApp().getId();
		}
		
		return appId;
	}
	

    

	 @Override
    protected NotificationExample getEntityExampleObject() { return new NotificationExample(); }

    

    
	@Override
	protected boolean isChannelEnabled(Long userId, String eventJson, KNotificationChannel channel) {
	    User user = userService.fetchById(userId);

		Configuration config = getUserConfig(user);
        
        Map<String,Object> event = KJsonUtil.toMap(eventJson);
        
        String type = "";
        
        if (event != null && event.get("type") != null) {
        	type = event.get("type").toString();
        }
        
        
        // in_app, email, sms, push
        String channelName = channel.getName().toLowerCase();
        channelName = KInflector.getInstance().camelCase(channelName, false);
        
        // "notification.friendship.email"
        String configParam = "notification." + type + "." + channelName;
        
        return config.getBoolean(configParam, false);
    }



	protected void sendNotification(Notification notification, NotificationDelivery delivery) {
		KNotificationChannel channel = KNotificationChannel.getInstance(delivery.getChannelId());
	}


	
	//urlTemplate.notification = https://example.com/#/notifications/{code}
	protected String createNotificationUrl(String code) {
		String url = config.getString("urlTemplate.app.notifications");
		url = url.replaceAll("\\{code\\}", code);
		return url;
	}
	

}
