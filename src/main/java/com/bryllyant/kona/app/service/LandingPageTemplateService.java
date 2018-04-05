/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.LandingPageTemplate;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LandingPageTemplateService extends KService, KEntityService<LandingPageTemplate> {
	String SERVICE_PATH = "rpc/LandingPageTemplateService";

    LandingPageTemplate fetchBySlug(String slug);

    List<LandingPageTemplate> fetchByOwnerId(Long ownerId);

    LandingPageTemplate create(Long ownerId, String name, String description, byte[] data, String contentType);

    LandingPageTemplate updateFile(LandingPageTemplate template, byte[] data, String contentType);

    @Transactional
    LandingPageTemplate updateFile(LandingPageTemplate template, File file);
	
}
