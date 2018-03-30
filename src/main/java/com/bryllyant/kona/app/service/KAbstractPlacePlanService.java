/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KPlacePlan;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractPlacePlanService<
        PLACE_PLAN extends KPlacePlan,
        PLACE_PLAN_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<PLACE_PLAN, PLACE_PLAN_EXAMPLE>>
        extends KAbstractService<PLACE_PLAN,PLACE_PLAN_EXAMPLE,MAPPER>
		implements KPlacePlanService<PLACE_PLAN> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractPlacePlanService.class);

    @Override
    protected boolean entityHasBlobs() {
        return true;
    }

    @Override
    public void validate(PLACE_PLAN plan) {
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
    public PLACE_PLAN fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
    public PLACE_PLAN fetchByPlaceIdAndSlug(Long placeId, String slug) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("slug", slug);
        filter.put("placeId", placeId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
    public List<PLACE_PLAN> fetchByPlaceId(Long placeId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("placeId", placeId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }


}