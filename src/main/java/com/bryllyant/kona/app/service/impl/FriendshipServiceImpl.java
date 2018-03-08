package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.FriendshipMapper;
import com.bryllyant.kona.app.entity.Friendship;
import com.bryllyant.kona.app.entity.FriendshipEvent;
import com.bryllyant.kona.app.entity.FriendshipExample;
import com.bryllyant.kona.app.entity.KFriendshipEvent;
import com.bryllyant.kona.app.service.FriendshipEventService;
import com.bryllyant.kona.app.service.FriendshipService;
import com.bryllyant.kona.app.service.KAbstractFriendshipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(FriendshipService.SERVICE_PATH)
public class FriendshipServiceImpl 
		extends KAbstractFriendshipService<Friendship, FriendshipExample, FriendshipMapper,FriendshipEvent>
		implements FriendshipService {
	
	private static Logger logger = LoggerFactory.getLogger(FriendshipServiceImpl.class);

	@Autowired
	private FriendshipMapper friendshipMapper;
	
	@Autowired
	FriendshipEventService friendshipEventService;
	
	@Autowired
	//NotificationService notificationService;
	


	@Override @SuppressWarnings("unchecked")
	protected FriendshipMapper getMapper() {
		return friendshipMapper;
	}
	


	@Override @SuppressWarnings("unchecked")
	protected FriendshipEventService getFriendshipEventService() {
		return friendshipEventService;
	}



	@Override
	protected Friendship getNewFriendshipObject() {
		return new Friendship();
	}



	@Override
	protected FriendshipEvent getNewFriendshipEventObject() {
		return new FriendshipEvent();
	}




	 @Override
    protected FriendshipExample getEntityExampleObject() { return new FriendshipExample(); }

	

	
	@Override
	protected void notifyEvent(Friendship friendship, KFriendshipEvent event) {
		logger.debug("notifyEvent called: friendship: {}  friendshipEvent: {}", friendship, event);
		
		switch (event.getType()) {
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
