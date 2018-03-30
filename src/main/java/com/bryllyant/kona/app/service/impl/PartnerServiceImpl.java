/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PartnerMapper;
import com.bryllyant.kona.app.entity.Partner;
import com.bryllyant.kona.app.entity.PartnerExample;
import com.bryllyant.kona.app.entity.Place;
import com.bryllyant.kona.app.service.KAbstractPartnerService;
import com.bryllyant.kona.app.service.PartnerService;
import com.bryllyant.kona.app.service.PlaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(PartnerService.SERVICE_PATH)
public class PartnerServiceImpl 
		extends KAbstractPartnerService<Partner, PartnerExample, PartnerMapper,Place>
		implements PartnerService {
	
	private static Logger logger = LoggerFactory.getLogger(PartnerServiceImpl.class);
    
    @Autowired
    private PartnerMapper partnerMapper;

    @Autowired
    private PlaceService placeService;


    @Override @SuppressWarnings("unchecked")
    protected PartnerMapper getMapper() {
        return partnerMapper;
    }

    @Override @SuppressWarnings("unchecked")
    protected PlaceService getPlaceService() {
        return placeService;
    }

    @Override
    protected PartnerExample getEntityExampleObject() { return new PartnerExample(); }

    

//    @Override
//    protected void updateCoords(Long partnerId) {
//        getMapper().updateCoords(partnerId);
//    }
//
//
//    @Override
//    public List<Partner> fetchProximate(Double latitude, Double longitude, Double radius, Date startDate, Date endDate) {
//        return getMapper().selectProximate(latitude, longitude, radius, startDate, endDate);
//    }

}
