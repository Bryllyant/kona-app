/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KFile;
import com.bryllyant.kona.app.entity.KLandingPageTemplate;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface KLandingPageTemplateService<
        TEMPLATE extends KLandingPageTemplate,
        FILE extends KFile>
        extends KService, KEntityService<TEMPLATE> {

	TEMPLATE fetchByUid(String uid);

    TEMPLATE fetchBySlug(String slug);

    List<TEMPLATE> fetchByOwnerId(Long ownerId);

	TEMPLATE create(Long ownerId, String name, String description, byte[] data, String contentType);

	TEMPLATE updateFile(TEMPLATE template, byte[] data, String contentType);

    @Transactional
    TEMPLATE updateFile(TEMPLATE template, FILE file);
}
