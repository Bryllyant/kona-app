/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.api.security.user;

import com.bryllyant.kona.api.security.token.AccessToken;
import com.bryllyant.kona.api.service.ApiAuthService;
import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.service.KAuthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

/**
 * UserPassAuthProvider
 */
public class UserPassAuthProvider implements AuthenticationProvider {
	private static Logger logger = LoggerFactory.getLogger(UserPassAuthProvider.class);
    
	@Autowired
	private UserSpringAuthService userSpringAuthService;

	@Autowired
	private ApiAuthService apiAuthService;
    

	/**
	 * Returns an authentication object based on the username and password values contained in
	 * the authentication parameter. To do so, it uses a
	 * {@link UserSpringAuthService}.
	 * 
	 * @throws AuthenticationException
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, authentication,
				"Only UsernamePasswordAuthenticationToken is supported");

		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
		
		String username = auth.getName();
		String password = (String) auth.getCredentials();

		AccessToken authResult = null;

		logger.debug("Trying to validate password for username: " + username);
		
		if (username == null || password == null) {
			throw new BadCredentialsException("Invalid username and/or password.");
		}

		
		Token token = null;
		
		try {
			token = apiAuthService.directLogin(username, password);
		} catch (KAuthException e) {
            logger.debug(e.getMessage(), e);
            throw new com.bryllyant.kona.rest.exception.AuthenticationException("Invalid username and/or password.");
        }
		
		UserDetails userDetails = userSpringAuthService.loadUserByUsername(token.getAccessToken());

		logger.debug("Authentication userDetails: " + userDetails);

		authResult = new AccessToken(
			userDetails, 
			userDetails.getAuthorities(), 
			token.getAccessToken(), 
			token.getRefreshToken(), 
			token.getAccessExpirationDate(), 
			token.getRefreshExpirationDate(), 
			token.getScope()
		);

		SecurityContextHolder.getContext().setAuthentication(authResult);
		
		logger.debug("Authentication result: " + authResult);

		return authResult;
	}

    
	// The default spring-security <http> tag will search for an auth provider that supports
	// UsernamePasswordAuthenticationToken to process the login form.
	@Override
	public boolean supports(Class<?> authentication) {
		logger.debug("----- checking support for authentication: " + authentication);
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
