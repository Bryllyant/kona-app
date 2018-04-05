/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PlacePlanMapper;
import com.bryllyant.kona.app.entity.PlacePlan;
import com.bryllyant.kona.app.entity.PlacePlanExample;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.PlacePlanService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service(PlacePlanService.SERVICE_PATH)
public class PlacePlanServiceImpl
        extends KAbstractService<PlacePlan,PlacePlanExample,PlacePlanMapper>
        implements PlacePlanService {

    private static Logger logger = LoggerFactory.getLogger(PlacePlanServiceImpl.class);

    @Autowired
    private PlacePlanMapper placePlanMapper;

    @Override
    protected boolean entityHasBlobs() {
        return true;
    }

    @Override @SuppressWarnings("unchecked")
    protected PlacePlanMapper getMapper() {
        return placePlanMapper;
    }

    @Override
    public void validate(PlacePlan plan) {
        if (plan.getCreatedDate() == null) {
            plan.setCreatedDate(new Date());
        }

        if (plan.getUid() == null) {
            plan.setUid(uuid());
        }

        plan.setUpdatedDate(new Date());

        plan.setSlug(KInflector.getInstance().slug(plan.getName()));
    }


    @Override
    public PlacePlan fetchByPlaceIdAndSlug(Long placeId, String slug) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("slug", slug);
        filter.put("placeId", placeId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
    public List<PlacePlan> fetchByPlaceId(Long placeId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("placeId", placeId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }
}