package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.FriendshipEventMapper;
import com.bryllyant.kona.app.entity.FriendshipEvent;
import com.bryllyant.kona.app.entity.FriendshipEventExample;
import com.bryllyant.kona.app.service.FriendshipEventService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(FriendshipEventService.SERVICE_PATH)
public class FriendshipEventServiceImpl 
		extends KAbstractService<FriendshipEvent, FriendshipEventExample, FriendshipEventMapper>
		implements FriendshipEventService {
	
	private static Logger logger = LoggerFactory.getLogger(FriendshipEventServiceImpl.class);

	@Autowired
	private FriendshipEventMapper eventMapper;
	
	@Override @SuppressWarnings("unchecked")
	protected FriendshipEventMapper getMapper() {
		return eventMapper;
	}
	

    @Override
    public void validate(FriendshipEvent friendshipEvent) {
        if (friendshipEvent.getCreatedDate() == null) {
            friendshipEvent.setCreatedDate(new Date());
        }

        friendshipEvent.setUpdatedDate(new Date());

        if (friendshipEvent.getUid() == null) {
            friendshipEvent.setUid(uuid());
        }
    }

    @Override
    public List<FriendshipEvent> fetchByFriendshipId(Long friendshipId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("friendshipId", friendshipId);
        return fetchByCriteria(filter);
    }
}
