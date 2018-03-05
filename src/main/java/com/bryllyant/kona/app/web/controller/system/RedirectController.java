/*
 * Copyright (C) 2017 Bryllyant, Inc  All Rights Reserved.
 */
package com.bryllyant.kona.app.web.controller.system;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bryllyant.kona.http.KServletUtil;

import com.bryllyant.kona.app.api.controller.BaseController;
import com.bryllyant.kona.app.entity.Redirect;
import com.bryllyant.kona.app.entity.ShortUrl;
import com.bryllyant.kona.app.service.RedirectService;
import com.bryllyant.kona.app.service.ShortUrlService;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

/**
 * Redirect Controller.
 */
@Controller
@RequestMapping("/system/redirect")
public class RedirectController extends BaseController {
    private static Logger logger = Logger.getLogger(RedirectController.class);
    


    @Autowired
    private ShortUrlService shortUrlService; 

    @Autowired
    private RedirectService redirectService; 


    
	@RequestMapping(value="/{shortUrlPath}", method=RequestMethod.GET)
	public void redirect(HttpServletRequest req, HttpServletResponse resp,
			@PathVariable final String shortUrlPath) throws IOException {
        try {
            logger.debug("SystemController:redirect called for: " + shortUrlPath);
        	doRedirect(req, resp, shortUrlPath);
        } catch (Exception e) {
            logger.error(e);
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource not found: " + shortUrlPath);
        }
	}
	

    
	private void doRedirect(HttpServletRequest req, HttpServletResponse resp,
			String path) throws IOException {
        Date now = new Date();
        
        ShortUrl surl = shortUrlService.fetchByPath(path);
        if (surl == null) {
            throw new IOException("ShortUrl path not found: " + path);
        }
        String redirectUrl = explode(req, surl);
        
        Redirect redirect = new Redirect();
        redirect.setShortUrlId(surl.getId());
        redirect.setPromoId(surl.getPromoId());
        redirect.setRequestUrl(KServletUtil.getFullRequestURL(req));
        redirect.setRedirectUrl(redirectUrl);
        redirect.setHostname(KServletUtil.getClientHostname(req));
        redirect.setUserAgent(KServletUtil.getClientUserAgent(req));
        redirect.setReferer(KServletUtil.getClientReferer(req));
        redirect.setLocale(KServletUtil.getClientLocale(req));
        redirect.setRequestedDate(now);
        redirect.setRedirectedDate(now);
        redirect.setCreatedDate(now);
        
        redirect = redirectService.add(redirect);
        
        String url = resp.encodeRedirectURL(redirectUrl);
        resp.sendRedirect(url);
	}
	

	
    @SuppressWarnings("unused")
    private String explode(HttpServletRequest req, String url) {
    	String longUrl = null;
    	ShortUrl shortUrl = null;
    	try {
    		URL u = new URL(url);
    		shortUrl = shortUrlService.fetchByShortUrl(url);
    	} catch (MalformedURLException e) {
    		shortUrl = shortUrlService.fetchByPath(url);
    	}

    	if (shortUrl != null) {
    		if (shortUrl.isScript()) {
    			longUrl = evalScript(req, shortUrl.getLongUrl());

    		} else {
    			longUrl = shortUrl.getLongUrl();
    		}
    	}
    	return longUrl;
    }
    

    
    private String explode(HttpServletRequest req, ShortUrl shortUrl) {
    	String longUrl = null;
    	if (shortUrl != null) {
    		if (shortUrl.isScript()) {
    			longUrl = evalScript(req, shortUrl.getLongUrl());

    		} else {
    			longUrl = shortUrl.getLongUrl();
    		}
    	}
    	return longUrl;
    }
    

    
	private String evalScript(HttpServletRequest req, String script) {
		Binding binding = new Binding();
		GroovyShell shell = new GroovyShell(binding);
		binding.setVariable("_req", req);
        String result = null;
		Object value = shell.evaluate(script);
        if (value != null) {
        	result = value.toString();
        }
        return result;
	}
}
