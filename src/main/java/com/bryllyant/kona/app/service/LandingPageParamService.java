/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.LandingPage;
import com.bryllyant.kona.app.entity.LandingPageParam;
import com.bryllyant.kona.app.entity.LandingPageTemplateParam;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface LandingPageParamService extends KService, KEntityService<LandingPageParam> {
	String SERVICE_PATH = "rpc/LandingPageParamService";

    LandingPageParam fetchByUid(String uid);

    LandingPageParam fetchByLandingPageIdAndTemplateParamId(Long landingPageId, Long templateParamId);

    List<LandingPageParam> fetchByLandingPageId(Long landingPageId);

    List<LandingPageParam> fetchByTemplateParamId(Long templateParamId);

    LandingPageParam save(LandingPage landingPage, LandingPageTemplateParam templateParam, String value);

    LandingPageParam save(LandingPage landingPage, LandingPageTemplateParam templateParam, byte[] data, String contentType);

    LandingPageParam updateFile(LandingPageParam param, byte[] data, String contentType);

    LandingPageParam updateFile(LandingPageParam param, File file);
}
