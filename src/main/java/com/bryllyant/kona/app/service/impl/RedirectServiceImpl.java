/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.RedirectMapper;
import com.bryllyant.kona.app.entity.Redirect;
import com.bryllyant.kona.app.entity.RedirectExample;
import com.bryllyant.kona.app.entity.ShortUrl;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.RedirectService;
import com.bryllyant.kona.app.service.ShortUrlService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.http.KServletUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(RedirectService.SERVICE_PATH)
public class RedirectServiceImpl 
		extends KAbstractService<Redirect, RedirectExample, RedirectMapper>
		implements RedirectService {
	
	private static Logger logger = LoggerFactory.getLogger(RedirectServiceImpl.class);

	@Autowired
	private RedirectMapper redirectMapper;
    
	@Autowired
	ShortUrlService shortUrlService;
    

	@Override @SuppressWarnings("unchecked")
	protected RedirectMapper getMapper() {
		return redirectMapper;
	}
    

    @Override
    public void validate(Redirect redirect) {
        if (redirect.getCreatedDate() == null) {
            redirect.setCreatedDate(new Date());
        }

        if (redirect.getUid() == null) {
            redirect.setUid(uuid());
        }

        redirect.setUpdatedDate(new Date());
    }



    @Override
    public List<Redirect> fetchByShortUrlId(Long shortUrlId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("shortUrlId", shortUrlId);
        return fetchByCriteria(0,9999, null, filter,  false);
    }


    @Override
    public List<Redirect> fetchByHostname(String hostname) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("hostname", hostname);
        return fetchByCriteria(0,9999, null, filter,  false);
    }


    @Override
    public Redirect buildRedirect(HttpServletRequest req, String path) throws IOException {
        logger.debug("RedirectService: redirect() called for: " + path);

        Date now = new Date();

        ShortUrl surl = shortUrlService.fetchByPath(path);

        if (surl == null) {
            throw new IOException("ShortUrl path not found: " + path);
        }

        String redirectUrl = shortUrlService.explode(req, surl);

        Redirect redirect = new Redirect();
        redirect.setShortUrlId(surl.getId());
        redirect.setRequestUrl(KServletUtil.getFullRequestURL(req));
        redirect.setRedirectUrl(redirectUrl);
        redirect.setHostname(KServletUtil.getClientHostname(req));
        redirect.setUserAgent(KServletUtil.getClientUserAgent(req));
        redirect.setReferer(KServletUtil.getClientReferer(req));
        redirect.setLocale(KServletUtil.getClientLocale(req));
        redirect.setLatitude(KServletUtil.getClientLatitude(req));
        redirect.setLongitude(KServletUtil.getClientLongitude(req));
        redirect.setRequestedDate(now);
        redirect.setRedirectedDate(now);

        redirect = add(redirect);
        return redirect;
    } 
}
