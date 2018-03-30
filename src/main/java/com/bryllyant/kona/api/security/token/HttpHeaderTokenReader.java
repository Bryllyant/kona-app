/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.api.security.token;

import com.bryllyant.kona.config.KConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


/**
 * Reads the token from a configurable HTTP Header
 */
@Service
public class HttpHeaderTokenReader implements TokenReader {
	private static Logger logger = LoggerFactory.getLogger(HttpHeaderTokenReader.class);
	
    @Autowired
    private KConfig config;
    

    /**
     * @return the token from the header {@link #headerName}, null otherwise
     */
    @Override
    public AccessToken findToken(HttpServletRequest req) {
        AccessToken token = null;
        String tokenValue = null;
        
        if (config == null) {
        	throw new IllegalStateException("Configuration not Autowired");
        }
        
        String headerName = config.getString("system.api.header.paramName.apiKey", "X-API-KEY");
        
        if (headerName != null) {
        	tokenValue = req.getHeader(headerName);
        }
        
        // If token is not in the header, see if it's a request parameter
        if (tokenValue == null) {
            tokenValue = req.getParameter("client_id");
        }

        if (tokenValue != null) {
            token = new AccessToken(tokenValue);
        } 
        
       //logger.debug("clientId: token: " + token);
        
        return token;
    }
}
