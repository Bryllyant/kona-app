package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.NotificationMapper;
import com.bryllyant.kona.app.entity.Notification;
import com.bryllyant.kona.app.entity.NotificationDelivery;
import com.bryllyant.kona.app.entity.NotificationExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.NotificationDeliveryService;
import com.bryllyant.kona.app.service.NotificationService;
import com.bryllyant.kona.app.service.SettingService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import com.bryllyant.kona.util.KJsonUtil;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.MapConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(NotificationService.SERVICE_PATH)
public class NotificationServiceImpl 
		extends KAbstractService<Notification, NotificationExample, NotificationMapper>
		implements NotificationService {
	
	private static Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);
	
	@Autowired
	private KConfig config;
	
	@Autowired
	private NotificationMapper notificationMapper;
	
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
	protected NotificationMapper getMapper() {
		return notificationMapper;
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
	

	protected boolean isChannelEnabled(Long userId, String eventJson, NotificationDelivery.Channel channel) {
	    User user = userService.fetchById(userId);

		Configuration config = getUserConfig(user);
        
        Map<String,Object> event = KJsonUtil.toMap(eventJson);
        
        String type = "";
        
        if (event != null && event.get("type") != null) {
        	type = event.get("type").toString();
        }
        
        
        // in_app, email, sms, push
        String channelName = channel.name().toLowerCase();
        channelName = KInflector.getInstance().camelCase(channelName, false);
        
        // "notification.friendship.email"
        String configParam = "notification." + type + "." + channelName;
        
        return config.getBoolean(configParam, false);
    }



	protected void sendNotification(Notification notification, NotificationDelivery delivery) {
		//Notification.Channel channel = delivery.getChannel();
	}


	
	//urlTemplate.notification = https://example.com/#/notifications/{code}
	protected String createNotificationUrl(String code) {
		String url = config.getString("urlTemplate.app.notifications");
		url = url.replaceAll("\\{code\\}", code);
		return url;
	}


    @Override
    protected boolean entityHasBlobs() {
        return true;
    }

    @Override
    public void validate(Notification notification) {
        if (notification.getCreatedDate() == null) {
            notification.setCreatedDate(new Date());
        }

        notification.setUpdatedDate(new Date());

        if (notification.getUid() == null) {
            notification.setUid(uuid());
        }
    }



    @Override
    public Notification fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public List<Notification> fetchByUserIdSinceUid(Long userId, String uid, Integer limit) {
        Date eventDate = null;

        if (uid != null) {
            Notification notification = fetchByUid(uid);

            if (notification != null) {
                eventDate = notification.getEventDate();
            }
        }

        if (limit == null) {
            limit = 100;
        }

        String[] sortOrder = { "eventDate DESC" };

        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);

        if (eventDate != null) {
            filter.put(">eventDate", eventDate);
        }

        return fetchByCriteria(0, limit, sortOrder, filter, false);
    }



    @Override
    public void notifyEvent(Long userId, String event, Date eventDate) {

        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setEventDate(eventDate);
        notification.setCreatedDate(new Date());

        notification = add(notification);

        NotificationDelivery.Channel channels[] = NotificationDelivery.Channel.values();

        for (NotificationDelivery.Channel channel : channels) {
            if (isChannelEnabled(userId, event, channel)) {
                try {
                    NotificationDelivery delivery = createDelivery(notification, channel);
                    sendNotification(notification, delivery);
                } catch (Exception e) {
                    logger.error("Error sending notificaiton: ", e);
                }
            }
        }
    }



    protected NotificationDelivery createDelivery(Notification notification, NotificationDelivery.Channel channel) {

        NotificationDelivery delivery = new NotificationDelivery();
        delivery.setUid(uuid());
        delivery.setNotificationId(notification.getId());
        delivery.setChannel(channel);
        delivery.setViewCount(0);
        delivery.setSentDate(new Date());
        delivery.setCreatedDate(new Date());
        delivery.setUpdatedDate(new Date());

        return notificationDeliveryService.add(delivery);
    }
}
