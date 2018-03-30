/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KFile;
import com.bryllyant.kona.app.entity.KLandingPage;
import com.bryllyant.kona.app.entity.KLandingPageParam;
import com.bryllyant.kona.app.entity.KLandingPageTemplate;
import com.bryllyant.kona.app.entity.KLandingPageTemplateParam;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractLandingPageService<
        PAGE extends KLandingPage,
        PAGE_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<PAGE, PAGE_EXAMPLE>,
        PAGE_PARAM extends KLandingPageParam,
        TEMPLATE extends KLandingPageTemplate,
        TEMPLATE_PARAM extends KLandingPageTemplateParam,
        FILE extends KFile>
        extends KAbstractService<PAGE,PAGE_EXAMPLE,MAPPER>
        implements KLandingPageService<PAGE, TEMPLATE> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractLandingPageService.class);

    protected abstract <S extends KLandingPageParamService<PAGE_PARAM,PAGE,TEMPLATE_PARAM,FILE>> S getLandingPageParamService();
    protected abstract <S extends KLandingPageTemplateParamService<TEMPLATE_PARAM>> S getTemplateParamService();

    @Override
    public void validate(PAGE page) {
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
    public PAGE fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public PAGE fetchBySlug(String slug) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public List<PAGE> fetchByTemplateId(Long templateId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("templateId", templateId);
        return fetchByCriteria(filter);
    }

    @Override @Transactional
    public PAGE create(TEMPLATE template, String name) {
        PAGE page = getEntityObject();

        page.setTemplateId(template.getId());
        page.setName(name);
        page.setEnabled(true);

        page = save(page);

        // See if the template has any params and if so create our matching list of params

        List<TEMPLATE_PARAM> templateParams = getTemplateParamService().fetchByTemplateId(template.getId());

        for (TEMPLATE_PARAM templateParam : templateParams) {
            PAGE_PARAM param = getLandingPageParamService().save(page, templateParam, null);
        }

        return page;
    }

}
