/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import java.util.Date;
import java.util.List;

import com.bryllyant.kona.app.entity.KPosition;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

public interface KPositionService<POSITION extends KPosition> extends KService, KEntityService<POSITION> {

    POSITION fetchByUid(String uid);


    List<POSITION> fetchByUserIdBetweenDates(Long userId, Date startDate, Date endDate);


    List<POSITION> fetchByUserIdBetweenSampleNos(Long userId, Long startSampleNo, Long endSampleNo);


    POSITION add(POSITION position, boolean updateUser);


    void addPositions(List<POSITION> positions, boolean updateUser);
    

    List<POSITION> fetchProximate(
            Double latitude, 
            Double longitude, 
            Double radius, 
            Date startDate, 
            Date endDate,
            List<Long> objectIdList
    );
}
