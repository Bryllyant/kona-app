package com.bryllyant.kona.app.api.controller.me;

import com.bryllyant.kona.app.api.controller.BaseController;
import com.bryllyant.kona.app.api.model.notification.NotificationModel;
import com.bryllyant.kona.app.api.service.NotificationModelService;
import com.bryllyant.kona.app.entity.Notification;
import com.bryllyant.kona.app.service.NotificationService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.rest.exception.ForbiddenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * UserNotification Controller.
 */
@RestController
@RequestMapping("/api/me/notifications")
public class MyNotificationController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(MyNotificationController.class);
	

    
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationModelService notificationModelService;


    
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<NotificationModel>> search(HttpServletRequest req,
			@RequestParam(value="last_uid", required=false) String lastUid,
			@RequestParam(value="limit", required=false) Integer limit) {
		logApiRequest(req, "GET /me/notifications");

        Long userId = getUser().getId();
        
		if (limit == null) {
			limit = 50;
		}

		List<Notification> result = notificationService.fetchByUserIdSinceUid(userId, lastUid, limit);
		return okList(notificationModelService.toModelList(result));
	}


	
	@RequestMapping(value="/{uid}", method=RequestMethod.GET)
	public ResponseEntity<NotificationModel> get(HttpServletRequest req,
			@PathVariable String uid) {
		logApiRequest(req, "GET /me/notifications/" + uid);
		
        Notification notification = notificationModelService.getNotification(uid);
        Long userId = getUser().getId();
        
        if (!notification.getUserId().equals(userId)) {
        	throw new ForbiddenException("User is not notification owner");
        }
        
		return ok(notificationModelService.toModel(notification));
	}
	



	
	@RequestMapping(value = "/{uid}", method=RequestMethod.PUT)
	public ResponseEntity<NotificationModel> update(HttpServletRequest req,
			@PathVariable String uid,
			@RequestBody Map<String,Object> map) {
		logApiRequest(req, "PUT /me/notifications/" + uid);
		
        Notification notification = notificationModelService.getNotification(uid);
        Long userId = getUser().getId();
        
        if (!notification.getUserId().equals(userId)) {
            throw new ForbiddenException("User is not notification owner");
        }  
        
        notification.setLastViewedDate(new Date());
        
        notification = notificationService.update(notification);

		return ok(notificationModelService.toModel(notification));
	}


	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Map<String,Object>> updateAll(HttpServletRequest req) {
		logApiRequest(req, "PUT /me/notifications");
		
        Long userId = getUser().getId();
        
        Integer startRow = 0;
        Integer resultSize = 999999;
        String[] sortOrder = null;
        Boolean distinct = false;

        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        filter.put("lastViewedDate", null);
        
        List<Notification> result = notificationService.fetchByCriteria(
                startRow, 
                resultSize, 
                sortOrder, 
                filter, 
                distinct);
        
        Date now = new Date();

        Integer updateCount = 0;

        for (Notification n : result) {
        	n.setLastViewedDate(now);
        	notificationService.update(n);
        	updateCount += 1;
        }

		return ok(getResultObject("update_count", updateCount));
	}
	


}
