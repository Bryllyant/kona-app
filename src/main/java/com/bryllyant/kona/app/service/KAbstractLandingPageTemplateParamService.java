/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KLandingPageTemplateParam;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import com.bryllyant.kona.util.KResultList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

public abstract class KAbstractLandingPageTemplateParamService<
        PARAM extends KLandingPageTemplateParam,
        PARAM_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<PARAM, PARAM_EXAMPLE>>
        extends KAbstractService<PARAM,PARAM_EXAMPLE,MAPPER>
        implements KLandingPageTemplateParamService<PARAM> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractLandingPageTemplateParamService.class);

    @Override
    public void validate(PARAM param) {
        if (param.getCreatedDate() == null) {
            param.setCreatedDate(new Date());
        }

        if (param.getUid() == null) {
            param.setUid(uuid());
        }

        param.setUpdatedDate(new Date());

        param.setSlug(KInflector.getInstance().slug(param.getName()));

        if (param.getDisplayName() == null) {
            param.setDisplayName(param.getName());
        }
    }


    @Override @Transactional
    public PARAM save(Long templateId, String name, String displayName, KLandingPageTemplateParam.Type type, boolean required) {
        String slug = KInflector.getInstance().slug(name);

        PARAM param = fetchByTemplateIdAndSlug(templateId, slug);

        if (param == null) {
            param = getEntityObject();
            param.setTemplateId(templateId);
            param.setName(name);
            param.setSlug(name);
            param.setDisplayName(displayName);
        }

        param.setType(type);
        param.setRequired(required);

        return save(param);
    }


    @Override
    public PARAM fetchByTemplateIdAndSlug(Long templateId, String slug) {
        Map<String,Object> filter = KMyBatisUtil.filter()
                .and("templateId", templateId)
                .and("slug", slug)
                .build();

        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
    public PARAM fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public KResultList<PARAM> fetchByTemplateId(Long templateId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("templateId", templateId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

}
