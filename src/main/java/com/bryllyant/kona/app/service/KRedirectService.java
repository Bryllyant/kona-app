/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bryllyant.kona.app.entity.KRedirect;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;


public interface KRedirectService<REDIRECT extends KRedirect> 
        extends KService, KEntityService<REDIRECT> {

    public List<REDIRECT> fetchByShortUrlId(Long shortUrlId);
    public List<REDIRECT> fetchByHostname(String hostname);
    public REDIRECT buildRedirect(HttpServletRequest req, String path) throws IOException;
}
