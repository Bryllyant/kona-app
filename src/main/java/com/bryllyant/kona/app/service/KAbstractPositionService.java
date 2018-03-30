/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KDevice;
import com.bryllyant.kona.app.entity.KPosition;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.app.entity.KUserDevice;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class KAbstractPositionService<
            POSITION extends KPosition, 
            POSITION_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<POSITION, POSITION_EXAMPLE>,
            USER extends KUser,
            DEVICE extends KDevice,
            USER_DEVICE extends KUserDevice>
		extends KAbstractService<POSITION,POSITION_EXAMPLE,MAPPER>
		implements KPositionService<POSITION> {



	private static Logger logger = LoggerFactory.getLogger(KAbstractPositionService.class);



	protected abstract void updateCoords(Long positionId);
	
	protected abstract <S extends KUserService<USER>> S getUserService();

	protected abstract <S extends KUserDeviceService<USER_DEVICE,USER,DEVICE>> S getUserDeviceService();



	private Long lastSampleNo = 0L;



	private Cache<String, USER_DEVICE> cache = Caffeine.newBuilder()
	        .expireAfterWrite(60, TimeUnit.MINUTES)
	        .maximumSize(10_000)
	        .build();


	
	protected String[] getDefaultSortOrder() {
		String[] sortOrder = { "sampleNo" };
		return sortOrder;
	}
	


    @Override @Transactional
    public POSITION add(POSITION position) {
        position = super.add(position);
        updateCoords(position.getId());
        return position;
    }
    


    @Override @Transactional
    public POSITION update(POSITION position) {
        position = super.update(position);
        updateCoords(position.getId());
        return position;
    }



	@Override @Transactional
	public POSITION add(POSITION position, boolean updateUser) {
	    position = this.add(position);

	    if (updateUser && position.getUserId() != null) {
	        USER user = getUserService().fetchById(position.getUserId());
	        user.setPositionId(position.getId());
	        getUserService().update(user);
	    }

	    return position;
	}



	@Transactional
	private USER_DEVICE checkUserDevice(POSITION position) {
	    Long userId = position.getUserId();

	    Long deviceId = position.getDeviceId();

	    if (userId == null || deviceId == null) return null;

	    String key = userId + "_" + deviceId;

	    USER_DEVICE userDevice = cache.getIfPresent(key);

	    if (userDevice == null) {
	        userDevice = getUserDeviceService().fetchByUserIdAndDeviceId(userId, deviceId);

	        if (userDevice == null) {
	            userDevice = getUserDeviceService().create(userId, deviceId, null);
	        }

	        cache.put(key, userDevice);
	    }

	    return userDevice;
	}



	@Override 
	public void validate(POSITION position) {
		if (position.getCreatedDate() == null) {
			position.setCreatedDate(new Date());
		}

		position.setUpdatedDate(new Date());

		if (position.getUid() == null) {
			position.setUid(uuid());
		}
		
		if (position.getCapturedDate() == null) {
		    position.setCapturedDate(new Date());
		}
		
		if (position.getSampleNo() == null) {
		    Long sampleNo = position.getCapturedDate().getTime();

		    if (sampleNo <= lastSampleNo) {
		        sampleNo = lastSampleNo + 1;
		    }

		    lastSampleNo = sampleNo;

			position.setSampleNo(sampleNo);
		}
		
		checkUserDevice(position);
	}
	


    @Override
    public POSITION fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



	@Override
	public List<POSITION> fetchByUserIdBetweenDates(Long userId, Date startDate, Date endDate) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		
		if (startDate != null) {
			filter.put(">positionDate", startDate);
		}
		
		if (endDate != null) {
			filter.put("<positionDate", endDate);
		}
		
		return fetchByCriteria(0, 99999, null, filter, false);
	}
	


	@Override
	public List<POSITION> fetchByUserIdBetweenSampleNos(Long userId, Long startSampleNo, Long endSampleNo) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		
		if (startSampleNo != null) {
			filter.put(">sampleNo", startSampleNo); 
		}
		
		if (endSampleNo != null) {
			filter.put("<sampleNo", endSampleNo);
		}
		
		return fetchByCriteria(0, 99999, null, filter, false);
	}


	
	@Override
	public void addPositions(List<POSITION> positions, boolean updateUser) {
	    // list may contain positions for different users so keep track of latest position for each user
	    Map<Long, POSITION> map = new HashMap<>();
	    
	    // sort positions by timestamp so latest position is at the end of the list
	    Collections.sort(positions, new Comparator<POSITION>() {

            @Override
            public int compare(POSITION o1, POSITION o2) {
                if (o1.getPositionDate().getTime() < o2.getPositionDate().getTime()) {
                    return -1;
                }

                if (o1.getPositionDate().getTime() > o2.getPositionDate().getTime()) {
                    return 1;
                }

                return 0;
            }
	    });

	    for (POSITION position : positions) {
	        position = add(position);

	        // since positions are sorted last put will hold the latest position for the user
	        if (updateUser) {
	            map.put(position.getUserId(), position);
	        }
	    }
	    
	    if (updateUser) {
	        for (Long userId : map.keySet()) {
	            // get the latest position for the user and save it
	            POSITION position = map.get(userId);

	            USER user = getUserService().fetchById(userId);

	            user.setPositionId(position.getId());
	            
	            user = getUserService().update(user);
	        }
	    }
	}
}
