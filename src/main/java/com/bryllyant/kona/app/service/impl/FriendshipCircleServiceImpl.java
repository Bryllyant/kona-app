package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.FriendshipCircleMapper;
import com.bryllyant.kona.app.entity.FriendshipCircle;
import com.bryllyant.kona.app.entity.FriendshipCircleExample;
import com.bryllyant.kona.app.service.FriendshipCircleService;
import com.bryllyant.kona.app.service.KAbstractFriendshipCircleService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(FriendshipCircleService.SERVICE_PATH)
public class FriendshipCircleServiceImpl 
		extends KAbstractFriendshipCircleService<FriendshipCircle,FriendshipCircleExample> 
		implements FriendshipCircleService {
	
	private static Logger logger = LoggerFactory.getLogger(FriendshipCircleServiceImpl.class);

	@Autowired
	private FriendshipCircleMapper circleDao;
	


	@Override @SuppressWarnings("unchecked")
	protected FriendshipCircleMapper getDao() {
		return circleDao;
	}
	

	
	 @Override
    protected FriendshipCircleExample getEntityExampleObject() { return new FriendshipCircleExample(); }

	


}
