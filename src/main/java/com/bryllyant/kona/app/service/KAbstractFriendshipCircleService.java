/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KFriendshipCircle;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractFriendshipCircleService<CIRCLE extends KFriendshipCircle, CIRCLE_EXAMPLE extends KEntityExample, MAPPER extends KMyBatisMapper<CIRCLE, CIRCLE_EXAMPLE>> extends KAbstractService<CIRCLE, CIRCLE_EXAMPLE,MAPPER>
        implements KFriendshipCircleService<CIRCLE> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractFriendshipCircleService.class);



    @Override
    public void validate(CIRCLE circle) {
        if (circle.getCreatedDate() == null) {
            circle.setCreatedDate(new Date());
        }

        circle.setUpdatedDate(new Date());

        if (circle.getUid() == null) {
            circle.setUid(uuid());
        }

        String slug = KInflector.getInstance().slug(circle.getName());

        circle.setSlug(slug);
    }



    @Override
    public CIRCLE fetchByUid(String uid) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public List<CIRCLE> fetchByUserId(Long userId) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }



    @Override
    public CIRCLE fetchDefaultByUserId(Long userId) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("userId", userId);
        filter.put("defaultCircle", true);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public CIRCLE fetchByUserIdAndSlug(Long userId, String slug) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("userId", userId);
        filter.put("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }
}
