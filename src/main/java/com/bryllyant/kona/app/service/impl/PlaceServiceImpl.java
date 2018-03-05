/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PlaceMapper;
import com.bryllyant.kona.app.entity.Place;
import com.bryllyant.kona.app.entity.PlaceExample;
import com.bryllyant.kona.app.service.GeocodingService;
import com.bryllyant.kona.app.service.KAbstractPlaceService;
import com.bryllyant.kona.app.service.PlaceService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(PlaceService.SERVICE_PATH)
public class PlaceServiceImpl 
		extends KAbstractPlaceService<Place,PlaceExample> 
		implements PlaceService {
	
	private static Logger logger = LoggerFactory.getLogger(PlaceServiceImpl.class);

	@Autowired
	private PlaceMapper placeDao;
	
	@Autowired
	GeocodingService geocodingService;



	@Override @SuppressWarnings("unchecked")
	protected PlaceMapper getDao() {
		return placeDao;
	}
	

	
	@Override @SuppressWarnings("unchecked")
	protected GeocodingService getGeocodingService() {
		return geocodingService;
	}
	


	 @Override
    protected PlaceExample getEntityExampleObject() { return new PlaceExample(); }

	


    @Override 
    protected void updateCoords(Long placeId) {
        getDao().updateCoords(placeId);
    }



    @Override 
    public List<Place> fetchProximate(
    		Double latitude,
			Double longitude,
			Double radius,
			Date startDate,
			Date endDate,
			List<Long> objectIdList
	) {
        return getDao().selectProximate(
        		latitude,
				longitude,
				radius,
				startDate,
				endDate,
                objectIdList
        );
    }



}
