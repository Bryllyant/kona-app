/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KLandingPage;
import com.bryllyant.kona.app.entity.KLandingPageTemplate;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface KLandingPageService<
        PAGE extends KLandingPage,
        TEMPLATE extends KLandingPageTemplate>
        extends KService, KEntityService<PAGE> {

	PAGE fetchByUid(String uid);

    PAGE fetchBySlug(String slug);

	List<PAGE> fetchByTemplateId(Long templateId);

    @Transactional
    PAGE create(TEMPLATE template, String name);
}
