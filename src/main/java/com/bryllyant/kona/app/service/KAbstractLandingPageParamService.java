/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KFile;
import com.bryllyant.kona.app.entity.KLandingPage;
import com.bryllyant.kona.app.entity.KLandingPageParam;
import com.bryllyant.kona.app.entity.KLandingPageTemplateParam;
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

public abstract class KAbstractLandingPageParamService<
            PARAM extends KLandingPageParam,
            PARAM_EXAMPLE extends KEntityExample,
            MAPPER extends KMyBatisMapper<PARAM, PARAM_EXAMPLE>,
            LANDING_PAGE extends KLandingPage,
            TEMPLATE_PARAM extends KLandingPageTemplateParam,
            USER extends KUser,
            FILE extends KFile>
		extends KAbstractService<PARAM,PARAM_EXAMPLE,MAPPER>
		implements KLandingPageParamService<PARAM,LANDING_PAGE,TEMPLATE_PARAM,FILE> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractLandingPageParamService.class);

	protected abstract PARAM getNewObject();
    protected abstract FILE getNewFileObject();
    protected abstract USER getSystemUser();

	protected abstract <S extends KFileService<FILE>> S getFileService();
    protected abstract <S extends KLandingPageTemplateParamService<TEMPLATE_PARAM>> S getTemplateParamService();

    @Override
    protected boolean entityHasBlobs() {
        return true;
    }

    @Override @Transactional
    public void remove(PARAM param) {
        if (param.getFileId() != null) {
            getFileService().removeById(param.getFileId());
        }

        super.remove(param);
    }

	@Override 
	public void validate(PARAM param) {
		if (param.getCreatedDate() == null) {
			param.setCreatedDate(new Date());
		}

		if (param.getUid() == null) {
			param.setUid(uuid());
		}

		param.setUpdatedDate(new Date());
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
            throw new KServiceException(e);
        }

        return file;
    }



    @Override @Transactional
    public PARAM save(LANDING_PAGE landingPage, TEMPLATE_PARAM templateParam, String value) {
        PARAM param = fetchByLandingPageIdAndTemplateParamId(landingPage.getId(), templateParam.getId());

        if (param == null) {
            param = getNewObject();
            param.setLandingPageId(landingPage.getId());
            param.setTemplateParamId(templateParam.getId());
        }

        if (value != null && param.getFileId() != null) {
            getFileService().removeById(param.getFileId());
            param.setFileId(null);
        }

        if (value != null && templateParam.getType() == TEMPLATE_PARAM.Type.MEDIA) {
            throw new KServiceException("save: value is not null and template param type is 'MEDIA'");
        }

        param.setValue(value);

        return save(param);
    }

    @Override @Transactional
    public PARAM save(LANDING_PAGE landingPage, TEMPLATE_PARAM templateParam, byte[] data, String contentType) {
        // sanity check
        if (templateParam.getType() != TEMPLATE_PARAM.Type.MEDIA) {
            throw new KServiceException("save: trying to save file but template param type equals: " + templateParam.getType());
        }

        PARAM param = fetchByLandingPageIdAndTemplateParamId(landingPage.getId(), templateParam.getId());

        if (param == null) {
            param = getNewObject();
            param.setLandingPageId(landingPage.getId());
            param.setTemplateParamId(templateParam.getId());
        }

        if (param.getFileId() != null) {
            getFileService().removeById(param.getFileId());
        }

        String filename = "landing-page-param-" + KInflector.getInstance().slug(templateParam.getName());

        FILE file = toFile(data, contentType, filename);
        file = getFileService().add(file);

        param.setFileId(file.getId());
        param.setValue(file.getUrlPath());

        return save(param);
    }


	@Override @Transactional
	public PARAM updateFile(PARAM param, byte[] data, String contentType) {
        TEMPLATE_PARAM templateParam = getTemplateParamService().fetchById(param.getTemplateParamId());
        String filename = "landing-page-param-" + KInflector.getInstance().slug(templateParam.getName());

        FILE file = toFile(data, contentType, filename);

        return updateFile(param, file);
	}

    @Override @Transactional
    public PARAM updateFile(PARAM param, FILE file) {

        if (param.getFileId() != null) {
            getFileService().removeById(param.getFileId());
        }

        if (file.getName() == null) {
            TEMPLATE_PARAM templateParam = getTemplateParamService().fetchById(param.getTemplateParamId());
            String filename = "landing-page-param-" + KInflector.getInstance().slug(templateParam.getName());
            file.setName(filename);
        }

        file = getFileService().add(file);

        param.setFileId(file.getId());
        param.setValue(file.getUrlPath());

        return save(param);
    }


	@Override
	public PARAM fetchByUid(String uid) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

    @Override
    public PARAM fetchByLandingPageIdAndTemplateParamId(Long landingPageId, Long templateParamId) {
        Map<String,Object> filter = KMyBatisUtil.filter()
                .and("landingPageId", landingPageId)
                .and("templateParamId", templateParamId)
                .build();

        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


	@Override
	public KResultList<PARAM> fetchByLandingPageId(Long landingPageId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("landingPageId", landingPageId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}

    @Override
    public KResultList<PARAM> fetchByTemplateParamId(Long templateParamId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("templateParamId", templateParamId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

}
