/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KFile;
import com.bryllyant.kona.app.entity.KLandingPage;
import com.bryllyant.kona.app.entity.KLandingPageParam;
import com.bryllyant.kona.app.entity.KLandingPageTemplateParam;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import com.bryllyant.kona.util.KResultList;


public interface KLandingPageParamService<
        PARAM extends KLandingPageParam,
        LANDING_PAGE extends KLandingPage,
        TEMPLATE_PARAM extends KLandingPageTemplateParam,
        FILE extends KFile>
        extends KService, KEntityService<PARAM> {

	PARAM fetchByUid(String uid);

	PARAM fetchByLandingPageIdAndTemplateParamId(Long landingPageId, Long templateParamId);

	KResultList<PARAM> fetchByLandingPageId(Long landingPageId);

    KResultList<PARAM> fetchByTemplateParamId(Long templateParamId);

	PARAM save(LANDING_PAGE landingPage, TEMPLATE_PARAM templateParam, String value);

    PARAM save(LANDING_PAGE landingPage, TEMPLATE_PARAM templateParam, byte[] data, String contentType);

    PARAM updateFile(PARAM param, byte[] data, String contentType);

    PARAM updateFile(PARAM param, FILE file);

}
