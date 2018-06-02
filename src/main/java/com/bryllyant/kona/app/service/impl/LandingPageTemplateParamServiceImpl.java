/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.LandingPageTemplateParamMapper;
import com.bryllyant.kona.app.entity.LandingPageTemplateParam;
import com.bryllyant.kona.app.entity.LandingPageTemplateParamExample;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.LandingPageTemplateParamService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(LandingPageTemplateParamService.SERVICE_PATH)
public class LandingPageTemplateParamServiceImpl
        extends KAbstractService<
                LandingPageTemplateParam,
                LandingPageTemplateParamExample,
                LandingPageTemplateParamMapper>
        implements LandingPageTemplateParamService {

    private static Logger logger = LoggerFactory.getLogger(LandingPageTemplateParamServiceImpl.class);

    @Autowired
    private LandingPageTemplateParamMapper mapper;


    @Override @SuppressWarnings("unchecked")
    protected LandingPageTemplateParamMapper getMapper() {
        return mapper;
    }

    @Override
    public void validate(LandingPageTemplateParam param) {
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
    public LandingPageTemplateParam save(Long templateId, String name, String displayName, LandingPageTemplateParam.Type type, boolean required) {
        String slug = KInflector.getInstance().slug(name);

        LandingPageTemplateParam param = fetchByTemplateIdAndSlug(templateId, slug);

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
    public LandingPageTemplateParam fetchByTemplateIdAndSlug(Long templateId, String slug) {
        Map<String,Object> filter = KMyBatisUtil.filter()
                .and("templateId", templateId)
                .and("slug", slug)
                .build();

        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }


    @Override
    public LandingPageTemplateParam fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }



    @Override
    public List<LandingPageTemplateParam> fetchByTemplateId(Long templateId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("templateId", templateId);
        return fetchByCriteria(filter);
    }
}
