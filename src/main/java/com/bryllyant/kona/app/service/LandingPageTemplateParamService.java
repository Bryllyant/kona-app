/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.LandingPageTemplateParam;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface LandingPageTemplateParamService extends KService, KEntityService<LandingPageTemplateParam> {
	String SERVICE_PATH = "rpc/LandingPageTemplateParamService";

    LandingPageTemplateParam fetchByTemplateIdAndSlug(Long templateId, String slug);

    List<LandingPageTemplateParam> fetchByTemplateId(Long templateId);

    LandingPageTemplateParam save(
            Long templateId,
            String name,
            String displayName,
            LandingPageTemplateParam.Type type,
            boolean required
    );
	
}
