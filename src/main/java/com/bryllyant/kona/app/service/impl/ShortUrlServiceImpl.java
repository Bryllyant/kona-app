/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.dao.ShortUrlMapper;
import com.bryllyant.kona.app.entity.ShortUrl;
import com.bryllyant.kona.app.entity.ShortUrlExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.KAbstractShortUrlService;
import com.bryllyant.kona.app.service.ShortUrlService;
import com.bryllyant.kona.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(ShortUrlService.SERVICE_PATH)
public class ShortUrlServiceImpl 
		extends KAbstractShortUrlService<
        ShortUrl,
        ShortUrlExample,
        ShortUrlMapper,
        User>
		implements ShortUrlService {
	
	private static Logger logger = LoggerFactory.getLogger(ShortUrlServiceImpl.class);

	@Autowired
	private ShortUrlMapper shortUrlMapper;
    
	@Autowired
	private KConfig config;

    @Autowired
    private UserService userService;

	@Override @SuppressWarnings("unchecked")
	protected ShortUrlMapper getMapper() {
		return shortUrlMapper;
	}

    @Override @SuppressWarnings("unchecked")
    protected UserService getUserService() {
	    return userService;
    }

	@Override
	protected String getDefaultVanityDomain() {
		return config.getString("shortUrl.domain");
	}
    
	@Override
	protected boolean useHttps() {
        return config.getBoolean("shortUrl.https", false);
	}
}
