/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PositionMapper;
import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.Position;
import com.bryllyant.kona.app.entity.PositionExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.entity.UserDevice;
import com.bryllyant.kona.app.service.KAbstractPositionService;
import com.bryllyant.kona.app.service.PositionService;
import com.bryllyant.kona.app.service.UserDeviceService;
import com.bryllyant.kona.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(PositionService.SERVICE_PATH)
public class PositionServiceImpl 
		extends KAbstractPositionService<Position, PositionExample, PositionMapper,
		    User,
		    Device,
		    UserDevice> 
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



    @Override @SuppressWarnings("unchecked")
    protected UserService getUserService() {
        return userService;
    }
    


    @Override @SuppressWarnings("unchecked")
    protected UserDeviceService getUserDeviceService() {
        return userDeviceService;
    }



    @Override
    protected PositionExample getEntityExampleObject() { return new PositionExample(); }



    @Override 
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
}
