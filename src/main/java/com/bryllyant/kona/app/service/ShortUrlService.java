/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.ShortUrl;
import com.bryllyant.kona.remote.service.KService;

public interface ShortUrlService extends KService, KShortUrlService<ShortUrl> {
	String SERVICE_PATH = "rpc/ShortUrlService";
	
}
