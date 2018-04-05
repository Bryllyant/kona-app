/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.LandingPage;
import com.bryllyant.kona.app.entity.LandingPageTemplate;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LandingPageService extends KService, KEntityService<LandingPage> {
	String SERVICE_PATH = "rpc/LandingPageService";

    LandingPage fetchByUid(String uid);

    LandingPage fetchBySlug(String slug);

    List<LandingPage> fetchByTemplateId(Long templateId);

    @Transactional
    LandingPage create(LandingPageTemplate template, String name);
	
}
