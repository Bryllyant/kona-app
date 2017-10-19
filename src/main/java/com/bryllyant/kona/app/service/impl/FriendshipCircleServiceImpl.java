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
	
	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected FriendshipCircleMapper getDao() {
		return circleDao;
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	protected FriendshipCircleExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
			Map<String, Object> filter, boolean distinct) {
		FriendshipCircleExample example = new FriendshipCircleExample();

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

}
