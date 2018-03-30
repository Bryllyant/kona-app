/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.api.security.user;

import com.bryllyant.kona.api.security.token.AccessToken;
import com.bryllyant.kona.api.service.ApiAuthService;
import com.bryllyant.kona.app.entity.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;


/**
 * UserTokenAuthProvider
 */
public class UserTokenAuthProvider implements AuthenticationProvider {
    private static Logger logger = LoggerFactory.getLogger(UserTokenAuthProvider.class);

    @Autowired
    private UserSpringAuthService userSpringAuthService;
    
    @Autowired
    private ApiAuthService apiAuthService;
    

    
    /**
     * Returns an authentication object based on the token value contained in the authentication parameter. To do so,
     * it uses a {@link TokenStorageService}.
     * @throws AuthenticationException
     */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(AccessToken.class, authentication, "Only AccessToken is supported");

        AccessToken authReq = (AccessToken) authentication; 
        
        String accessToken = authReq.getValue();
        AccessToken authResult = new AccessToken(accessToken);

        if (accessToken != null) {
            logger.debug("Trying to validate token ${authReq.accessToken}");
            UserDetails userDetails = userSpringAuthService.loadUserByUsername(accessToken);
            
            Token token = apiAuthService.fetchTokenByAccessToken(accessToken);
            
            logger.debug("UserTokenAuthProvider: authenticate: token: " + token);

            if (token == null || !token.isActive()) {
                throw new BadCredentialsException("Invalid accessToken: " + accessToken);
            }
            
            authResult = new AccessToken(
            	userDetails, 
            	userDetails.getAuthorities(), 
            	accessToken, 
            	token.getRefreshToken(), 
            	token.getAccessExpirationDate(), 
            	token.getRefreshExpirationDate(), 
            	token.getScope()
            );
            
            logger.debug("Authentication result: " + authResult);
        }

        return authResult;
	}


    
	@Override
	public boolean supports(Class<?> authentication) {
        return AccessToken.class.isAssignableFrom(authentication);
	}
}
