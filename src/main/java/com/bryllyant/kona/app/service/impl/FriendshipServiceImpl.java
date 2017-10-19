package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.FriendshipMapper;
import com.bryllyant.kona.app.entity.Friendship;
import com.bryllyant.kona.app.entity.FriendshipEvent;
import com.bryllyant.kona.app.entity.FriendshipExample;
import com.bryllyant.kona.app.entity.KFriendshipEvent;
import com.bryllyant.kona.app.entity.KFriendshipEventType;
import com.bryllyant.kona.app.service.FriendshipEventService;
import com.bryllyant.kona.app.service.FriendshipService;
import com.bryllyant.kona.app.service.KAbstractFriendshipService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(FriendshipService.SERVICE_PATH)
public class FriendshipServiceImpl 
		extends KAbstractFriendshipService<Friendship,FriendshipExample,FriendshipEvent> 
		implements FriendshipService {
	
	private static Logger logger = LoggerFactory.getLogger(FriendshipServiceImpl.class);

	@Autowired
	private FriendshipMapper friendshipDao;
	
	@Autowired
	FriendshipEventService friendshipEventService;
	
	@Autowired
	//NotificationService notificationService;
	
	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected FriendshipMapper getDao() {
		return friendshipDao;
	}
	
	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected FriendshipEventService getFriendshipEventService() {
		return friendshipEventService;
	}

	// ----------------------------------------------------------------------------

	@Override
	protected Friendship getNewFriendshipObject() {
		return new Friendship();
	}

	// ----------------------------------------------------------------------------

	@Override
	protected FriendshipEvent getNewFriendshipEventObject() {
		return new FriendshipEvent();
	}


	// ----------------------------------------------------------------------------

	@Override
	protected FriendshipExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
			Map<String, Object> filter, boolean distinct) {
		FriendshipExample example = new FriendshipExample();

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
	
	@Override
	protected void notifyEvent(Friendship friendship, KFriendshipEvent event) {
		logger.debug("notifyEvent called: friendship: {}  friendshipEvent: {}", friendship, event);
		
		KFriendshipEventType type = KFriendshipEventType.getInstance(event.getTypeId());
		
		switch (type) {
		case FOLLOW:
			break;
			
		case FRIENDSHIP_ACCEPT:
			break;
			
		case FRIENDSHIP_REQUEST:
			break;
			
		case BLOCK:
		case UNFOLLOW:
		case FRIENDSHIP_REJECT:
		case FRIENDSHIP_REVOKE:
		case UNBLOCK:
			break;
		default:
			break;
			
		}
	}

}
