/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.LandingPageMapper;
import com.bryllyant.kona.app.entity.LandingPage;
import com.bryllyant.kona.app.entity.LandingPageExample;
import com.bryllyant.kona.app.entity.LandingPageParam;
import com.bryllyant.kona.app.entity.LandingPageTemplate;
import com.bryllyant.kona.app.entity.LandingPageTemplateParam;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.LandingPageParamService;
import com.bryllyant.kona.app.service.LandingPageService;
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

@Service(LandingPageService.SERVICE_PATH)
public class LandingPageServiceImpl
		extends KAbstractService<LandingPage,LandingPageExample,LandingPageMapper>
		implements LandingPageService {
	
	private static Logger logger = LoggerFactory.getLogger(LandingPageServiceImpl.class);

	@Autowired
	private LandingPageMapper mapper;

    @Autowired
    private LandingPageTemplateParamService templateParamService;

    @Autowired
    private LandingPageParamService landingPageParamService;

	@Override @SuppressWarnings("unchecked")
	protected LandingPageMapper getMapper() {
		return mapper;
	}


    @Override
    public void validate(LandingPage page) {
        if (page.getCreatedDate() == null) {
            page.setCreatedDate(new Date());
        }

        if (page.getUid() == null) {
            page.setUid(uuid());
        }

        page.setUpdatedDate(new Date());

        page.setSlug(KInflector.getInstance().slug(page.getName()));
    }


    @Override
    public LandingPage fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public LandingPage fetchBySlug(String slug) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public List<LandingPage> fetchByTemplateId(Long templateId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("templateId", templateId);
        return fetchByCriteria(filter);
    }

    @Override @Transactional
    public LandingPage create(LandingPageTemplate template, String name) {
        LandingPage page = getEntityObject();

        page.setTemplateId(template.getId());
        page.setName(name);
        page.setEnabled(true);

        page = save(page);

        // See if the template has any params and if so create our matching list of params

        List<LandingPageTemplateParam> templateParams = templateParamService.fetchByTemplateId(template.getId());

        for (LandingPageTemplateParam templateParam : templateParams) {
            LandingPageParam param = landingPageParamService.save(page, templateParam, null);
        }

        return page;
    }

}
