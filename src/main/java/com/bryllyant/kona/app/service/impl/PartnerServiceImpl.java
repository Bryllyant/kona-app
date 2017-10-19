/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PartnerMapper;
import com.bryllyant.kona.app.entity.Partner;
import com.bryllyant.kona.app.entity.PartnerExample;
import com.bryllyant.kona.app.service.KAbstractPartnerService;
import com.bryllyant.kona.app.service.PartnerService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(PartnerService.SERVICE_PATH)
public class PartnerServiceImpl 
		extends KAbstractPartnerService<Partner,PartnerExample> 
		implements PartnerService {
	
	private static Logger logger = LoggerFactory.getLogger(PartnerServiceImpl.class);
    
    @Autowired
    private PartnerMapper partnerDao;


    // ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected PartnerMapper getDao() {
        return partnerDao;
    }
    
    // ----------------------------------------------------------------------------

    @Override
    protected PartnerExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
            Map<String, Object> filter, boolean distinct) {
    	PartnerExample example = new PartnerExample();

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

    @Override 
    protected void updateCoords(Long partnerId) {
        getDao().updateCoords(partnerId);
    }

    // ----------------------------------------------------------------------------

    @Override 
    public List<Partner> fetchProximate(Double latitude, Double longitude, Double radius, Date startDate, Date endDate) {
        return getDao().selectProximate(latitude, longitude, radius, startDate, endDate);
    }

}
