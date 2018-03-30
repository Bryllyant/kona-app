/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KLandingPageTemplateParam;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import com.bryllyant.kona.util.KResultList;


public interface KLandingPageTemplateParamService<
        PARAM extends KLandingPageTemplateParam>
        extends KService, KEntityService<PARAM> {

	PARAM fetchByUid(String uid);

	PARAM fetchByTemplateIdAndSlug(Long templateId, String slug);

    KResultList<PARAM> fetchByTemplateId(Long templateId);

	PARAM save(
	        Long templateId,
            String name,
            String displayName,
            KLandingPageTemplateParam.Type type,
            boolean required
    );
}
