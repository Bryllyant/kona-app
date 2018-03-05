package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.FriendshipEventMapper;
import com.bryllyant.kona.app.entity.FriendshipEvent;
import com.bryllyant.kona.app.entity.FriendshipEventExample;
import com.bryllyant.kona.app.service.FriendshipEventService;
import com.bryllyant.kona.app.service.KAbstractFriendshipEventService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(FriendshipEventService.SERVICE_PATH)
public class FriendshipEventServiceImpl 
		extends KAbstractFriendshipEventService<FriendshipEvent,FriendshipEventExample> 
		implements FriendshipEventService {
	
	private static Logger logger = LoggerFactory.getLogger(FriendshipEventServiceImpl.class);

	@Autowired
	private FriendshipEventMapper eventDao;
	


	@Override @SuppressWarnings("unchecked")
	protected FriendshipEventMapper getDao() {
		return eventDao;
	}
	

	
	 @Override
    protected FriendshipEventExample getEntityExampleObject() { return new FriendshipEventExample(); }

	


}
