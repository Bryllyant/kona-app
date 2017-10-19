/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.api.security.user;

import com.bryllyant.kona.app.config.KConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserAuthEntryPoint extends LoginUrlAuthenticationEntryPoint {
	private static Logger logger = Logger.getLogger(UserAuthEntryPoint.class);
    
	@Autowired
	private KConfig config;

    public UserAuthEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
		String env = System.getProperty("env", "dev").trim().toLowerCase();
		if (env.equals("prd")) {
            logger.debug("PROD env detected: setting forceHttps to true");
			setForceHttps(true);
		}
	}
    
    /*
    @Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
        super.commence(request, response, authException);
	}
    */
   

    @Override
	protected String buildRedirectUrlToLoginPage(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException authException) {
        
        String url;
        if (isForceHttps()) {
        	String authBaseUrl = config.getString("system.api.baseUrl");
        	url = authBaseUrl + "login";
        } else {
        	url = super.buildRedirectUrlToLoginPage(request, response, authException);
        }
        
        url += "?" + request.getQueryString();
        logger.debug("buildRedirectUrlToLoginPage called: redirect URL: " + url);
        return url;
    }
    
    @Override
    protected String buildHttpsRedirectUrlForRequest(HttpServletRequest request)
			throws IOException, ServletException {
        //String url = super.buildHttpsRedirectUrlForRequest(request);
    	String authBaseUrl = config.getString("system.api.baseUrl");
        String url = authBaseUrl + "login";
        url += "?" + request.getQueryString();
        logger.debug("buildHttpsRedirectUrlForRequest called: redirect URL: " + url);
        return url;
    }
}
