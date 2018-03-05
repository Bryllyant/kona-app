/*
 * Copyright (C) 2017 Bryllyant, Inc  All Rights Reserved.
 */
package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.security.oauth2.OAuth2TokenServices;
import com.bryllyant.kona.app.api.security.token.AccessToken;
import com.bryllyant.kona.app.api.security.user.UserSpringAuthService;
import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AuthService;
import com.bryllyant.kona.app.service.TokenService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.util.KClassUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @version 1.0
 */
@Service
public class OAuth2TokenService implements ResourceServerTokenServices {
	private static Logger logger = Logger.getLogger(OAuth2TokenService.class);
    
	@Autowired
	private OAuth2TokenServices tokenServices;
    
	@Autowired
	private OAuth2RequestFactory oAuth2RequestFactory;
    
	@Autowired
	private AuthenticationManager userAuthManager;

	@Autowired
	private ClientDetailsService clientDetailsService;
	
	@Autowired
	private UserSpringAuthService userSpringAuthService;
    
	@Autowired
	private TokenService tokenService;
    
	@Autowired
	private UserService userService;
    
	@Autowired
	private AuthService authService;
	

    
	public Token login(String clientId, String username, String password) {
		ClientDetails client = clientDetailsService.loadClientByClientId(clientId);

		Map<String, String> params = new HashMap<String,String>(); 
        params.put("grant_type", "password");
        params.put("username", username);
        params.put("password", password);
        params.put("client_id", clientId);
        
		TokenRequest tokenRequest = oAuth2RequestFactory.createTokenRequest(params, client);
		OAuth2AccessToken oauth2AccessToken = null;
		
        try {
        	OAuth2Authentication auth = getOAuth2Authentication(client, tokenRequest);
        	oauth2AccessToken = tokenServices.createAccessToken(auth);
        } catch (Exception e ) {
            logger.debug("Could not authenticate username: " + username, e);
            return null;
        }
        
        //String accessToken = auth.getUserAuthentication().getName();
        String accessToken = oauth2AccessToken.getValue();
        
        Token token = tokenService.fetchByAccessToken(accessToken, false);
        
        if (token == null) {
            logger.debug("Could not authenticate username: " + username);
            return null;
        }
        
        return token;
	}
	

    
    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) 
            throws AuthenticationException, InvalidTokenException {

        // fetch token and check that it's valid
        Token token = tokenService.fetchByAccessToken(accessToken, true);
        
        if (token == null || token.getAuthentication() == null) {
            logger.debug("Token not found or is invalid: " + accessToken);
            throw new InvalidTokenException(accessToken);
        }

        byte[] data = token.getAuthentication();
        
        try {
            return KClassUtil.deserializeObjectFromByteArray(data);
        } catch (Exception e) {
            logger.error("loadAuthentication exception: " + e.getMessage(), e);
            throw new InvalidTokenException(accessToken);
        }
    }
    


    @Override
    public OAuth2AccessToken readAccessToken(String accessToken) {
        throw new UnsupportedOperationException("Not supported: read access token");
    }
    

	
	protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {

		Map<String, String> parameters = new LinkedHashMap<String, String>(tokenRequest.getRequestParameters());
		String username = parameters.get("username");
		String password = parameters.get("password");
		
		// Protect from downstream leaks of password
		parameters.remove("password");

		Authentication userAuth = new UsernamePasswordAuthenticationToken(username, password);
		
		((AbstractAuthenticationToken) userAuth).setDetails(parameters);
		
		try {
			userAuth = userAuthManager.authenticate(userAuth);
		}
		catch (AccountStatusException ase) {
			//covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
			throw new InvalidGrantException(ase.getMessage());
		}
		catch (BadCredentialsException e) {
			// If the username/password are wrong the spec says we should send 400/invalid grant
			throw new InvalidGrantException(e.getMessage());
		}
		
		if (userAuth == null || !userAuth.isAuthenticated()) {
			throw new InvalidGrantException("Could not authenticate user: " + username);
		}
		
		OAuth2Request storedOAuth2Request = oAuth2RequestFactory.createOAuth2Request(client, tokenRequest);		
		
		return new OAuth2Authentication(storedOAuth2Request, userAuth);
	}
	

	
	// USE WITH CARE: This assumes userId has already been authenticated.  Typical use case
	// would be generating an access_token for a developer already logged in through a
	// developer portal.  Also, note that this does not log this user into the current session
	// the current SecurityContext remains unchanged.  (This is different from the login()
	// method above which logs the user into the current security context.
	public Token createToken(String clientId, Long userId) {
		User user = userService.fetchById(userId);
		
		ClientDetails client = clientDetailsService.loadClientByClientId(clientId);
		
		Map<String, String> params = new HashMap<String,String>(); 
        params.put("grant_type", "password");
        params.put("username", user.getUsername());
        params.put("password", null);
        params.put("client_id", clientId);
        
		TokenRequest tokenRequest = oAuth2RequestFactory.createTokenRequest(params, client);
		
		Token token = authService.createToken(user, client.getClientId());

		Authentication userAuth = null;
		try {
			UserDetails userDetails = userSpringAuthService.loadUserByUsername(token.getAccessToken());

			userAuth = new AccessToken(userDetails, userDetails.getAuthorities(), 
					token.getAccessToken(), token.getRefreshToken(), 
					token.getAccessExpirationDate(), token.getRefreshExpirationDate(), 
					token.getScope());
		}
		catch (AccountStatusException ase) {
			//covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
			throw new InvalidGrantException(ase.getMessage());
		}
		catch (BadCredentialsException e) {
			// If the username/password are wrong the spec says we should send 400/invalid grant
			throw new InvalidGrantException(e.getMessage());
		}
		if (userAuth == null || !userAuth.isAuthenticated()) {
			throw new InvalidGrantException("Could not authenticate user: " + user.getUsername());
		}
		
		OAuth2Request storedOAuth2Request = oAuth2RequestFactory.createOAuth2Request(client, tokenRequest);		
		OAuth2Authentication auth = new OAuth2Authentication(storedOAuth2Request, userAuth);
		OAuth2AccessToken oauth2AccessToken = tokenServices.createAccessToken(auth);
		
		String accessToken = oauth2AccessToken.getValue();
        
		// this may be overkill but a good check to make sure everything worked as it should.
        token = tokenService.fetchByAccessToken(accessToken, false);
        
        if (token == null) {
            logger.debug("Could not authenticate username: " + user.getUsername());
            return null;
        }
        
        return token;
	}
    
}
