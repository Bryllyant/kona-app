/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.dao.ShortUrlMapper;
import com.bryllyant.kona.app.entity.ShortUrl;
import com.bryllyant.kona.app.entity.ShortUrlExample;
import com.bryllyant.kona.app.service.KAbstractShortUrlService;
import com.bryllyant.kona.app.service.ShortUrlService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(ShortUrlService.SERVICE_PATH)
public class ShortUrlServiceImpl 
		extends KAbstractShortUrlService<ShortUrl,ShortUrlExample> 
		implements ShortUrlService {
	
	private static Logger logger = LoggerFactory.getLogger(ShortUrlServiceImpl.class);

	@Autowired
	private ShortUrlMapper shortUrlDao;
    
	@Autowired
	private KConfig config;
    


	@Override @SuppressWarnings("unchecked")
	protected ShortUrlMapper getDao() {
		return shortUrlDao;
	}
    

    
	@Override
	protected ShortUrl getNewObject() {
		return new ShortUrl();
	}
    


	@Override
	protected String getDefaultVanityDomain() {
		return config.getString("shortUrl.domain");
	}
    


	@Override
	protected boolean useHttps() {
        return config.getBoolean("shortUrl.https", false);
	}
    


	 @Override
    protected ShortUrlExample getEntityExampleObject() { return new ShortUrlExample(); }

    
}
