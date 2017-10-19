/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.api.security.client;

import com.bryllyant.kona.app.api.security.token.AccessToken;
import com.bryllyant.kona.util.KJsonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * ClientAuthProvider
 */
public class ClientAuthProvider implements AuthenticationProvider, 
	AuthenticationSuccessHandler, AuthenticationFailureHandler, AuthenticationEventPublisher {
    private static Logger logger = Logger.getLogger(ClientAuthProvider.class);

    @Autowired
    private ClientAuthService clientAuthService;
    
    /**
     * Returns an authentication object based on the token value contained in the authentication parameter. To do so,
     * it uses a {@link TokenStorageService}.
     * @throws AuthenticationException
     */
	@Override
	public Authentication authenticate(Authentication authentication) 
    		
			throws AuthenticationException {
        Assert.isInstanceOf(AccessToken.class, authentication, "Only AccessToken is supported");

        AccessToken authReq = (AccessToken) authentication; 
        AccessToken authResult = new AccessToken(authReq.getValue());

        if (authReq.getValue() != null) {
            logger.debug("Trying to validate token: " + authReq.getValue());
            UserDetails userDetails = clientAuthService.loadUserByUsername(authReq.getValue());
            authResult = new AccessToken(userDetails, 
            					userDetails.getAuthorities(), 
            					authReq.getValue(),
            					null, null, null, null);
            
            SecurityContextHolder.getContext().setAuthentication(authResult);
            logger.debug("Authentication result: " + authResult);
        }

        return authResult;
	}

    
	@Override
	public boolean supports(Class<?> authentication) {
        return AccessToken.class.isAssignableFrom(authentication);
	}


	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
        //logger.debug("publishAuthenticationSuccess called: " + authentication);
	}


	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
        //logger.debug("publishAuthenticationFailure called: " + authentication);
	}


	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException auth)
			throws IOException, ServletException {
        logger.debug("onAuthenticationFailure called: " + auth);
        //resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        
        //throw new com.bryllyant.kona.api.exceptions.AuthenticationException(auth.getLocalizedMessage());
        resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, auth.getLocalizedMessage());
	}


	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth)
			throws IOException, ServletException {
        logger.debug("onAuthenticationSuccess called: " + auth);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.addHeader("Cache-Control", "no-store");
        resp.addHeader("Pragma", "no-cache");
        
        
        /*
			{
    	    "access_token": "YLAZSreh165CAD2tPhAEzFCYYIrVyFomLWUDMGFBZIw9KtIg4q",
    	    "expires_in": 3600,
    		"refresh_token": "Pgk+l9okjwTCfsvIvEDPrsomE1er1txeyoaAkTIBAuXza8WvZY",
    		"refresh_expires_in": 5184000,
    		"token_type": "bearer"
			}
         */
        
        AccessToken token = (AccessToken) auth;
        Map<String,Object> tokenMap = new HashMap<String,Object>();
        tokenMap.put("access_token", token.getValue());
        tokenMap.put("expires_in", token.getExpiresIn());
        tokenMap.put("refresh_token", token.getRefreshValue());
        tokenMap.put("refresh_expires_in", token.getRefreshExpiresIn());
        tokenMap.put("token_type", token.getTokenType());
        
        
        resp.getWriter().println(KJsonUtil.toJson(tokenMap));
	}
}
