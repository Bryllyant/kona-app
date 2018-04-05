/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Position;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.Date;
import java.util.List;

public interface PositionService extends KService, KEntityService<Position> {
	String SERVICE_PATH = "rpc/PositionService";

    List<Position> fetchByUserIdBetweenDates(Long userId, Date startDate, Date endDate);

    List<Position> fetchByUserIdBetweenSampleNos(Long userId, Long startSampleNo, Long endSampleNo);

    Position add(Position position, boolean updateUser);

    void addPositions(List<Position> positions, boolean updateUser);

    List<Position> fetchProximate(
            Double latitude,
            Double longitude,
            Double radius,
            Date startDate,
            Date endDate,
            List<Long> objectIdList
    );
	
}
