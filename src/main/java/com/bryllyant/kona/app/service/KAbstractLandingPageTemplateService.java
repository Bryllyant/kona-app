/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KFile;
import com.bryllyant.kona.app.entity.KLandingPageTemplate;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.app.util.KUtil;
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

public abstract class KAbstractLandingPageTemplateService<
        TEMPLATE extends KLandingPageTemplate,
        TEMPLATE_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<TEMPLATE, TEMPLATE_EXAMPLE>,
        USER extends KUser,
        FILE extends KFile>
        extends KAbstractService<TEMPLATE,TEMPLATE_EXAMPLE,MAPPER>
        implements KLandingPageTemplateService<TEMPLATE, FILE> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractLandingPageTemplateService.class);

    protected abstract FILE getNewFileObject();

    protected abstract USER getSystemUser();

    protected abstract <S extends KFileService<FILE>> S getFileService();

    @Override @Transactional
    public void remove(TEMPLATE template) {
        if (template.getFileId() != null) {
            getFileService().removeById(template.getFileId());
        }

        super.remove(template);
    }

    @Override
    public void validate(TEMPLATE template) {
        if (template.getCreatedDate() == null) {
            template.setCreatedDate(new Date());
        }

        if (template.getUid() == null) {
            template.setUid(uuid());
        }

        template.setUpdatedDate(new Date());

        String slug = KInflector.getInstance().slug(template.getName());

        template.setSlug(slug);
    }

    private FILE toFile(byte[] data, String contentType, String filename) {
        FILE file;

        if (!contentType.equals("application/zip")) {
            throw new KServiceException("Invalid contentType. Only application/zip is currently supported");
        }

        try {
            @SuppressWarnings("unchecked")
            Class<FILE> clazz = (Class<FILE>) getNewFileObject().getClass();

            String srcFilename = uuid();
            String srcHostname = "127.0.0.1";
            boolean tempFile = false;

            file = KUtil.toFile(clazz, getSystemUser(), data, contentType, filename, srcFilename, srcHostname, tempFile);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new KServiceException(e);
        }

        return file;
    }



    @Override @Transactional
    public TEMPLATE create(Long ownerId, String name, String description, byte[] data, String contentType) {
        try {
            TEMPLATE template = getEntityObject();

            template.setOwnerId(ownerId);
            template.setName(name);
            template.setDescription(description);

            if (data != null) {
                String filename = "landing-page-template-" + KInflector.getInstance().slug(name);

                FILE file = toFile(data, contentType, filename);

                file = getFileService().add(file);

                template.setFileId(file.getId());
                template.setUrlPath(file.getUrlPath());
            }

            return add(template);
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
            throw new KServiceException(t);
        }
    }

    @Override @Transactional
    public TEMPLATE updateFile(TEMPLATE template, byte[] data, String contentType) {
        try {

            String filename = "landing-page-template-" + template.getSlug();

            FILE file = toFile(data, contentType, filename);

            if (template.getFileId() != null) {
                getFileService().removeById(template.getFileId());
            }

            file = getFileService().add(file);

            template.setFileId(file.getId());

            return update(template);
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
            throw new KServiceException(t);
        }
    }

    @Override @Transactional
    public TEMPLATE updateFile(TEMPLATE template, FILE file) {
        try {

            String filename = "landing-page-template-" + template.getSlug();

            if (file.getName() == null) {
                file.setName(filename);
            }

            if (template.getFileId() != null) {
                getFileService().removeById(template.getFileId());
            }

            file = getFileService().add(file);

            template.setFileId(file.getId());

            return update(template);
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
            throw new KServiceException(t);
        }
    }

    @Override
    public TEMPLATE fetchBySlug(String slug) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

    @Override
    public TEMPLATE fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

    @Override
    public KResultList<TEMPLATE> fetchByOwnerId(Long ownerId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("ownerId", ownerId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

}
