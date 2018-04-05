package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.FriendshipCircleMapper;
import com.bryllyant.kona.app.entity.FriendshipCircle;
import com.bryllyant.kona.app.entity.FriendshipCircleExample;
import com.bryllyant.kona.app.service.FriendshipCircleService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(FriendshipCircleService.SERVICE_PATH)
public class FriendshipCircleServiceImpl 
		extends KAbstractService<FriendshipCircle, FriendshipCircleExample, FriendshipCircleMapper>
		implements FriendshipCircleService {
	
	private static Logger logger = LoggerFactory.getLogger(FriendshipCircleServiceImpl.class);

	@Autowired
	private FriendshipCircleMapper circleMapper;
	
	@Override @SuppressWarnings("unchecked")
	protected FriendshipCircleMapper getMapper() {
		return circleMapper;
	}
	

    @Override
    public void validate(FriendshipCircle circle) {
        if (circle.getCreatedDate() == null) {
            circle.setCreatedDate(new Date());
        }

        circle.setUpdatedDate(new Date());

        if (circle.getUid() == null) {
            circle.setUid(uuid());
        }

        String slug = KInflector.getInstance().slug(circle.getName());

        circle.setSlug(slug);
    }


    @Override
    public List<FriendshipCircle> fetchByUserId(Long userId) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }


    @Override
    public FriendshipCircle fetchDefaultByUserId(Long userId) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("userId", userId);
        filter.put("defaultCircle", true);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
    public FriendshipCircle fetchByUserIdAndSlug(Long userId, String slug) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("userId", userId);
        filter.put("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

}
