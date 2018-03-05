/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.RedirectMapper;
import com.bryllyant.kona.app.entity.Redirect;
import com.bryllyant.kona.app.entity.RedirectExample;
import com.bryllyant.kona.app.entity.ShortUrl;
import com.bryllyant.kona.app.service.KAbstractRedirectService;
import com.bryllyant.kona.app.service.RedirectService;
import com.bryllyant.kona.app.service.ShortUrlService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(RedirectService.SERVICE_PATH)
public class RedirectServiceImpl 
		extends KAbstractRedirectService<Redirect,RedirectExample,ShortUrl> 
		implements RedirectService {
	
	private static Logger logger = LoggerFactory.getLogger(RedirectServiceImpl.class);

	@Autowired
	private RedirectMapper redirectDao;
    
	@Autowired
	ShortUrlService shortUrlService;
    


	@Override @SuppressWarnings("unchecked")
	protected RedirectMapper getDao() {
		return redirectDao;
	}
    

    
	@Override
	protected Redirect getNewObject() {
		return new Redirect();
	}
	

	
	@Override @SuppressWarnings("unchecked")
	protected ShortUrlService getShortUrlService() {
		return shortUrlService;
	}
    


	 @Override
    protected RedirectExample getEntityExampleObject() { return new RedirectExample(); }

    
}
