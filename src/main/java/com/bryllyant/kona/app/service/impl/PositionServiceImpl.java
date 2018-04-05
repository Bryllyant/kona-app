/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PositionMapper;
import com.bryllyant.kona.app.entity.Position;
import com.bryllyant.kona.app.entity.PositionExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.entity.UserDevice;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.PositionService;
import com.bryllyant.kona.app.service.UserDeviceService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service(PositionService.SERVICE_PATH)
public class PositionServiceImpl 
		extends KAbstractService<Position, PositionExample, PositionMapper>
        implements PositionService {

    private static Logger logger = LoggerFactory.getLogger(PositionServiceImpl.class);

    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDeviceService userDeviceService;


    @Override @SuppressWarnings("unchecked")
    protected PositionMapper getMapper() {
        return positionMapper;
    }

    protected void updateCoords(Long positionId) {
        getMapper().updateCoords(positionId);
    }


    @Override
    public List<Position> fetchProximate(
            Double latitude,
            Double longitude,
            Double radius,
            Date startDate,
            Date endDate,
            List<Long> objectIdList
    ) {
        return getMapper().selectProximate(latitude, longitude, radius, startDate, endDate, objectIdList);
    }


    private Long lastSampleNo = 0L;



    private Cache<String, UserDevice> cache = Caffeine.newBuilder()
            .expireAfterWrite(60, TimeUnit.MINUTES)
            .maximumSize(10_000)
            .build();



    protected String[] getDefaultSortOrder() {
        return null;
    }



    @Override @Transactional
    public Position add(Position position) {
        position = super.add(position);
        updateCoords(position.getId());
        return position;
    }



    @Override @Transactional
    public Position update(Position position) {
        position = super.update(position);
        updateCoords(position.getId());
        return position;
    }



    @Override @Transactional
    public Position add(Position position, boolean updateUser) {
        position = this.add(position);

        if (updateUser && position.getUserId() != null) {
            User user = userService.fetchById(position.getUserId());
            user.setPositionId(position.getId());
            userService.update(user);
        }

        return position;
    }



    @Transactional
    private UserDevice checkUserDevice(Position position) {
        Long userId = position.getUserId();

        Long deviceId = position.getDeviceId();

        if (userId == null || deviceId == null) return null;

        String key = userId + "_" + deviceId;

        UserDevice userDevice = cache.getIfPresent(key);

        if (userDevice == null) {
            userDevice = userDeviceService.fetchByUserIdAndDeviceId(userId, deviceId);

            if (userDevice == null) {
                userDevice = userDeviceService.create(userId, deviceId, null);
            }

            cache.put(key, userDevice);
        }

        return userDevice;
    }



    @Override
    public void validate(Position position) {
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
    public Position fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public List<Position> fetchByUserIdBetweenDates(Long userId, Date startDate, Date endDate) {
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
    public List<Position> fetchByUserIdBetweenSampleNos(Long userId, Long startSampleNo, Long endSampleNo) {
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
    public void addPositions(List<Position> positions, boolean updateUser) {
        // list may contain positions for different users so keep track of latest position for each user
        Map<Long, Position> map = new HashMap<>();

        // sort positions by timestamp so latest position is at the end of the list
        Collections.sort(positions, new Comparator<Position>() {

            @Override
            public int compare(Position o1, Position o2) {
                if (o1.getPositionDate().getTime() < o2.getPositionDate().getTime()) {
                    return -1;
                }

                if (o1.getPositionDate().getTime() > o2.getPositionDate().getTime()) {
                    return 1;
                }

                return 0;
            }
        });

        for (Position position : positions) {
            position = add(position);

            // since positions are sorted last put will hold the latest position for the user
            if (updateUser) {
                map.put(position.getUserId(), position);
            }
        }

        if (updateUser) {
            for (Long userId : map.keySet()) {
                // get the latest position for the user and save it
                Position position = map.get(userId);

                User user = userService.fetchById(userId);

                user.setPositionId(position.getId());

                user = userService.update(user);
            }
        }
    }
}
