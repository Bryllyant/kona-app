/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Redirect;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface RedirectService extends KService, KEntityService<Redirect> {
	String SERVICE_PATH = "rpc/RedirectService";

    List<Redirect> fetchByShortUrlId(Long shortUrlId);
    List<Redirect> fetchByHostname(String hostname);
    Redirect buildRedirect(HttpServletRequest req, String path) throws IOException;
	
}
