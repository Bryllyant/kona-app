/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.LandingPageTemplateMapper;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.LandingPageTemplate;
import com.bryllyant.kona.app.entity.LandingPageTemplateExample;
import com.bryllyant.kona.app.service.FileService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.service.KServiceException;
import com.bryllyant.kona.app.service.LandingPageTemplateService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.AppUtil;
import com.bryllyant.kona.util.KInflector;
import com.bryllyant.kona.util.KResultList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

@Service(LandingPageTemplateService.SERVICE_PATH)
public class LandingPageTemplateServiceImpl
        extends KAbstractService<LandingPageTemplate, LandingPageTemplateExample, LandingPageTemplateMapper>
        implements LandingPageTemplateService {

    private static Logger logger = LoggerFactory.getLogger(LandingPageTemplateServiceImpl.class);

    @Autowired
    private LandingPageTemplateMapper mapper;

    @Autowired
    private SystemService system;

    @Autowired
    private FileService fileService;

    @Autowired
    private AppUtil appUtil;

    @Override
    protected LandingPageTemplateMapper getMapper() {
        return mapper;
    }


    @Override @Transactional
    public void remove(LandingPageTemplate template) {
        if (template.getFileId() != null) {
            fileService.removeById(template.getFileId());
        }

        super.remove(template);
    }

    @Override
    public void validate(LandingPageTemplate template) {
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

    private File toFile(byte[] data, String contentType, String filename) {
        File file;

        if (!contentType.equals("application/zip")) {
            throw new KServiceException("Invalid contentType. Only application/zip is currently supported");
        }

        String srcFilename = uuid();
        String srcHostname = "127.0.0.1";
        boolean tempFile = false;

        file = appUtil.toFile(system.getSystemUser(), data, contentType, filename, srcFilename, srcHostname, tempFile);

        return file;
    }



    @Override @Transactional
    public LandingPageTemplate create(Long ownerId, String name, String description, byte[] data, String contentType) {
        try {
            LandingPageTemplate template = getEntityObject();

            template.setOwnerId(ownerId);
            template.setName(name);
            template.setDescription(description);

            if (data != null) {
                String filename = "landing-page-template-" + KInflector.getInstance().slug(name);

                File file = toFile(data, contentType, filename);

                file = fileService.add(file);

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
    public LandingPageTemplate updateFile(LandingPageTemplate template, byte[] data, String contentType) {
        try {

            String filename = "landing-page-template-" + template.getSlug();

            File file = toFile(data, contentType, filename);

            if (template.getFileId() != null) {
                fileService.removeById(template.getFileId());
            }

            file = fileService.add(file);

            template.setFileId(file.getId());

            return update(template);
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
            throw new KServiceException(t);
        }
    }

    @Override @Transactional
    public LandingPageTemplate updateFile(LandingPageTemplate template, File file) {
        try {

            String filename = "landing-page-template-" + template.getSlug();

            if (file.getName() == null) {
                file.setName(filename);
            }

            if (template.getFileId() != null) {
                fileService.removeById(template.getFileId());
            }

            file = fileService.add(file);

            template.setFileId(file.getId());

            return update(template);
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
            throw new KServiceException(t);
        }
    }

    @Override
    public LandingPageTemplate fetchBySlug(String slug) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public LandingPageTemplate fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public KResultList<LandingPageTemplate> fetchByOwnerId(Long ownerId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("ownerId", ownerId);
        return fetchByCriteria(filter);
    }
}
