/*
 * Copyright (C) 2017 Bryllyant, Inc  All Rights Reserved.
 */
package com.bryllyant.kona.web.controller.system;

import com.bryllyant.kona.api.controller.BaseController;
import com.bryllyant.kona.app.entity.Redirect;
import com.bryllyant.kona.app.entity.ShortUrl;
import com.bryllyant.kona.app.service.RedirectService;
import com.bryllyant.kona.app.service.ShortUrlService;
import com.bryllyant.kona.http.KServletUtil;
import com.bryllyant.kona.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Redirect Controller.
 */
@Controller
@RequestMapping("/system/redirect")
public class RedirectController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(RedirectController.class);
    
    @Autowired
    private ShortUrlService shortUrlService; 

    @Autowired
    private RedirectService redirectService;

    @RequestMapping(value="/{shortUrlPath}/{extraPath:.*}", method=RequestMethod.GET)
    public void redirectWithExtraPath(HttpServletRequest req, HttpServletResponse resp,
                         @PathVariable final String shortUrlPath,
                         @PathVariable final String extraPath) throws IOException {
        try {
            logger.debug("SystemController:redirect called for: " + shortUrlPath);
            doRedirect(req, resp, shortUrlPath, extraPath);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource not found: " + shortUrlPath);
        }
    }


	@RequestMapping(value="/{shortUrlPath}", method=RequestMethod.GET)
	public void redirect(HttpServletRequest req, HttpServletResponse resp,
			@PathVariable final String shortUrlPath) throws IOException {
        redirectWithExtraPath(req, resp, shortUrlPath, null);
	}
	

    
	private void doRedirect(
	        HttpServletRequest req,
            HttpServletResponse resp,
			String path,
            String extraPath) throws IOException {
        Date now = new Date();

        String redirectUrl = null;

        ShortUrl surl = shortUrlService.fetchByPath(path);

        if (surl != null) {

            redirectUrl = shortUrlService.explode(req, surl);
        }

        if (redirectUrl == null) {
            throw new NotFoundException("ShortUrl path not found: " + path);
        }

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
        redirect.setCreatedDate(now);
        
        redirect = redirectService.add(redirect);
        
        String url = resp.encodeRedirectURL(redirectUrl);

        resp.sendRedirect(url);
	}
	

	
//    @SuppressWarnings("unused")
//    private String explode(HttpServletRequest req, String url) {
//    	String longUrl = null;
//    	ShortUrl shortUrl = null;
//    	try {
//    		URL u = new URL(url);
//    		shortUrl = shortUrlService.fetchByShortUrl(url);
//    	} catch (MalformedURLException e) {
//    		shortUrl = shortUrlService.fetchByPath(url);
//    	}
//
//    	if (shortUrl != null) {
//    		if (shortUrl.isScript()) {
//    			longUrl = evalScript(req, shortUrl.getLongUrl());
//
//    		} else {
//    			longUrl = shortUrl.getLongUrl();
//    		}
//    	}
//    	return longUrl;
//    }
//
//
//
//    private String explode(HttpServletRequest req, ShortUrl shortUrl) {
//    	String longUrl = null;
//    	if (shortUrl != null) {
//    		if (shortUrl.isScript()) {
//    			longUrl = evalScript(req, shortUrl.getLongUrl());
//
//    		} else {
//    			longUrl = shortUrl.getLongUrl();
//    		}
//    	}
//    	return longUrl;
//    }
//
//
//
//	private String evalScript(HttpServletRequest req, String script) {
//		Binding binding = new Binding();
//		GroovyShell shell = new GroovyShell(binding);
//		binding.setVariable("_req", req);
//        String result = null;
//		Object value = shell.evaluate(script);
//        if (value != null) {
//        	result = value.toString();
//        }
//        return result;
//	}
}
