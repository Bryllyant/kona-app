/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KFriendshipEvent;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractFriendshipEventService<FRIENDSHIP_EVENT extends KFriendshipEvent, FRIENDSHIP_EVENT_EXAMPLE extends KEntityExample, MAPPER extends KMyBatisMapper<FRIENDSHIP_EVENT, FRIENDSHIP_EVENT_EXAMPLE>> extends KAbstractService<FRIENDSHIP_EVENT,FRIENDSHIP_EVENT_EXAMPLE,MAPPER>
		implements KFriendshipEventService<FRIENDSHIP_EVENT> {
			
	private static Logger logger = LoggerFactory.getLogger(KAbstractFriendshipEventService.class);




    @Override
	public void validate(FRIENDSHIP_EVENT friendshipEvent) {
    	if (friendshipEvent.getCreatedDate() == null) {
			friendshipEvent.setCreatedDate(new Date());
		}
    	
    	friendshipEvent.setUpdatedDate(new Date());
    	
    	if (friendshipEvent.getUid() == null) {
            friendshipEvent.setUid(uuid());
        }
	}
    

	
	@Override
	public List<FRIENDSHIP_EVENT> fetchByFriendshipId(Long friendshipId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("friendshipId", friendshipId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
}
