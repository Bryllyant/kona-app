/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.LandingPageParamMapper;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.LandingPage;
import com.bryllyant.kona.app.entity.LandingPageParam;
import com.bryllyant.kona.app.entity.LandingPageParamExample;
import com.bryllyant.kona.app.entity.LandingPageTemplateParam;
import com.bryllyant.kona.app.service.FileService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.service.KServiceException;
import com.bryllyant.kona.app.service.LandingPageParamService;
import com.bryllyant.kona.app.service.LandingPageTemplateParamService;
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

@Service(LandingPageParamService.SERVICE_PATH)
public class LandingPageParamServiceImpl
		extends KAbstractService<LandingPageParam,LandingPageParamExample,LandingPageParamMapper>
		implements LandingPageParamService {
	
	private static Logger logger = LoggerFactory.getLogger(LandingPageParamServiceImpl.class);

	@Autowired
	private LandingPageParamMapper mapper;

    @Autowired
    private SystemService systemService;

    @Autowired
    private FileService fileService;
    
    @Autowired
    private AppUtil appUtil;

    @Autowired
    private LandingPageTemplateParamService templateParamService;

	@Override @SuppressWarnings("unchecked")
	protected LandingPageParamMapper getMapper() {
		return mapper;
	}

    @Override
    protected boolean entityHasBlobs() {
        return true;
    }

    @Override @Transactional
    public void remove(LandingPageParam param) {
        if (param.getFileId() != null) {
            fileService.removeById(param.getFileId());
        }

        super.remove(param);
    }

    @Override
    public void validate(LandingPageParam param) {
        if (param.getCreatedDate() == null) {
            param.setCreatedDate(new Date());
        }

        if (param.getUid() == null) {
            param.setUid(uuid());
        }

        param.setUpdatedDate(new Date());
    }

    private File toFile(byte[] data, String contentType, String filename) {
        File file;

        if (!contentType.equals("application/zip")) {
            throw new KServiceException("Invalid contentType. Only application/zip is currently supported");
        }

        try {
            String srcFilename = uuid();
            String srcHostname = "127.0.0.1";
            boolean tempFile = false;

            file = appUtil.toFile(systemService.getSystemUser(), data, contentType, filename, srcFilename, srcHostname, tempFile);
        } catch (Exception e) {
            throw new KServiceException(e);
        }

        return file;
    }



    @Override @Transactional
    public LandingPageParam save(LandingPage landingPage, LandingPageTemplateParam templateParam, String value) {
        LandingPageParam param = fetchByLandingPageIdAndTemplateParamId(landingPage.getId(), templateParam.getId());

        if (param == null) {
            param = new LandingPageParam();
            param.setLandingPageId(landingPage.getId());
            param.setTemplateParamId(templateParam.getId());
        }

        if (value != null && param.getFileId() != null) {
            fileService.removeById(param.getFileId());
            param.setFileId(null);
        }

        if (value != null && templateParam.getType() == LandingPageTemplateParam.Type.Media) {
            throw new KServiceException("save: value is not null and template param type is 'Media'");
        }

        param.setValue(value);

        return save(param);
    }

    @Override @Transactional
    public LandingPageParam save(LandingPage landingPage, LandingPageTemplateParam templateParam, byte[] data, String contentType) {
        // sanity check
        if (templateParam.getType() != LandingPageTemplateParam.Type.Media) {
            throw new KServiceException("save: trying to save file but template param type equals: " + templateParam.getType());
        }

        LandingPageParam param = fetchByLandingPageIdAndTemplateParamId(landingPage.getId(), templateParam.getId());

        if (param == null) {
            param = new LandingPageParam();
            param.setLandingPageId(landingPage.getId());
            param.setTemplateParamId(templateParam.getId());
        }

        if (param.getFileId() != null) {
            fileService.removeById(param.getFileId());
        }

        String filename = "landing-page-param-" + KInflector.getInstance().slug(templateParam.getName());

        File file = toFile(data, contentType, filename);
        file = fileService.add(file);

        param.setFileId(file.getId());
        param.setValue(file.getUrlPath());

        return save(param);
    }


    @Override @Transactional
    public LandingPageParam updateFile(LandingPageParam param, byte[] data, String contentType) {
        LandingPageTemplateParam templateParam = templateParamService.fetchById(param.getTemplateParamId());
        String filename = "landing-page-param-" + KInflector.getInstance().slug(templateParam.getName());

        File file = toFile(data, contentType, filename);

        return updateFile(param, file);
    }

    @Override @Transactional
    public LandingPageParam updateFile(LandingPageParam param, File file) {

        if (param.getFileId() != null) {
            fileService.removeById(param.getFileId());
        }

        if (file.getName() == null) {
            LandingPageTemplateParam templateParam = templateParamService.fetchById(param.getTemplateParamId());
            String filename = "landing-page-param-" + KInflector.getInstance().slug(templateParam.getName());
            file.setName(filename);
        }

        file = fileService.add(file);

        param.setFileId(file.getId());
        param.setValue(file.getUrlPath());

        return save(param);
    }


    @Override
    public LandingPageParam fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

    @Override
    public LandingPageParam fetchByLandingPageIdAndTemplateParamId(Long landingPageId, Long templateParamId) {
        Map<String,Object> filter = KMyBatisUtil.filter()
                .and("landingPageId", landingPageId)
                .and("templateParamId", templateParamId)
                .build();

        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
    public KResultList<LandingPageParam> fetchByLandingPageId(Long landingPageId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("landingPageId", landingPageId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    @Override
    public KResultList<LandingPageParam> fetchByTemplateParamId(Long templateParamId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("templateParamId", templateParamId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

}
