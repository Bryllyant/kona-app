/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.api.security.oauth2;

import com.bryllyant.kona.app.api.security.token.AccessToken;
import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AuthService;
import com.bryllyant.kona.app.service.TokenService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.rest.exception.SystemException;
import com.bryllyant.kona.util.KClassUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidScopeException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class OAuth2TokenServices extends DefaultTokenServices {
	private static Logger logger = Logger.getLogger(OAuth2TokenServices.class);

	@Autowired
	private TokenStore tokenStore;
	
	@Autowired
    private ClientDetailsService clientDetailsService;
    
	@Autowired
    private TokenService tokenService;
    
	@Autowired
    private UserService userService;
    
	@Autowired
    private AuthService authService;
    
	
	private boolean reuseRefreshToken = false;
	
	private boolean supportRefreshToken = true;
	
	@Override
	public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) {
		logger.debug("createAccessToken called for OAuth2Authentication: " + KClassUtil.toString(authentication));

		AccessToken token = (AccessToken) tokenStore.getAccessToken(authentication);
		
		OAuth2RefreshToken refreshToken = null;
		
		if (token != null) {
			if (token.isExpired()) {
				if (token.getRefreshToken() != null) {
					refreshToken = token.getRefreshToken();
					// The token store could remove the refresh token when the
					// access token is removed, but we want to
					// be sure...
					tokenStore.removeRefreshToken(refreshToken);
				}
				tokenStore.removeAccessToken(token);
			} else {
				// Restore the access token in case the authentication has changed
				tokenStore.storeAccessToken(token, authentication);
				return toOAuth2AccessToken(token);
			}
		} else {
            // When a user logs in, we generate an access token at that time which becomes the user's
			// Authentication object.  Since we already created a token, use that as our OAuth2AccessToken.
			token = (AccessToken) authentication.getUserAuthentication();
			
			logger.debug("createAccessToken: existing authentication object: " + KClassUtil.toString(token));
			
			tokenStore.storeAccessToken(token, authentication);

			Map<String,String> params = authentication.getOAuth2Request().getRequestParameters(); 
			
			for (String key : params.keySet()) { 
				token.addInfo(key, params.get(key)); 
			}

			logger.debug("Extracted AccessToken: " + KClassUtil.toString(token)); 
		}
		
		return toOAuth2AccessToken(token);
	}
	
	protected OAuth2AccessToken toOAuth2AccessToken(AccessToken token) {
		if (token == null) return null;
		logger.debug("OAuth2TokenServices: toOAuth2AccessToken: original token: " + KClassUtil.toString(token)); 
		
		OAuth2AccessToken t = new AccessToken(token.getValue(), token.getRefreshValue(),
				token.getExpiration(), token.getRefreshExpiration(), token.getScope());
		return t;
	}
    
	@Override
	public OAuth2Authentication loadAuthentication(String accessTokenValue) throws AuthenticationException,
	InvalidTokenException {
		OAuth2AccessToken accessToken = tokenStore.readAccessToken(accessTokenValue);
		if (accessToken == null) {
			throw new InvalidTokenException("Invalid access token: " + accessTokenValue);
		}
		else if (accessToken.isExpired()) {
			tokenStore.removeAccessToken(accessToken);
			throw new InvalidTokenException("Access token expired: " + accessTokenValue);
		}

		OAuth2Authentication result = tokenStore.readAuthentication(accessToken);
		if (result == null) {
			// in case of race condition
			throw new InvalidTokenException("Invalid access token: " + accessTokenValue);
		}
		if (clientDetailsService != null) {
			String clientId = result.getOAuth2Request().getClientId();
			try {
				clientDetailsService.loadClientByClientId(clientId);
			}
			catch (ClientRegistrationException e) {
				throw new InvalidTokenException("Client not valid: " + clientId, e);
			}
		}
		return result;
	}
    
	
    @Override
	public boolean revokeToken(String tokenValue) {
    	Token token = tokenService.fetchByAccessToken(tokenValue, false);
    	
        if (token == null) {
        	return false;
        }
        
        token.setApproved(false);
        token.setActive(false);
        token.setExpiredDate(new Date());
        tokenService.update(token);
        return true;
	}
    
    @Override @Transactional(noRollbackFor={InvalidTokenException.class, InvalidGrantException.class})
	public OAuth2AccessToken refreshAccessToken(String refreshTokenValue, TokenRequest tokenRequest)
			throws AuthenticationException {

		if (!supportRefreshToken) {
			throw new InvalidGrantException("Invalid refresh token: " + refreshTokenValue);
		}

		OAuth2RefreshToken refreshToken = tokenStore.readRefreshToken(refreshTokenValue);
		
		if (refreshToken == null) {
			throw new InvalidGrantException("Invalid refresh token: " + refreshTokenValue);
		}

		OAuth2Authentication authentication = tokenStore.readAuthenticationForRefreshToken(refreshToken);
        
		String clientId = authentication.getOAuth2Request().getClientId();
		
		if (clientId == null || !clientId.equals(tokenRequest.getClientId())) {
			throw new InvalidGrantException("Wrong client for this refresh token: " + refreshTokenValue);
		}

		// clear out any access tokens already associated with the refresh token.
		tokenStore.removeAccessTokenUsingRefreshToken(refreshToken);

		if (isExpired(refreshToken)) {
			tokenStore.removeRefreshToken(refreshToken);
			throw new InvalidTokenException("Invalid refresh token (expired): " + refreshToken);
		}

		authentication = createRefreshedAuthentication(authentication, tokenRequest);

        Token current = tokenService.fetchByRefreshToken(refreshTokenValue);
        
        if (current == null || current.getUserId() == null) {
        	throw new InvalidTokenException("Invalid refresh token: " + refreshTokenValue);
        }
        
        if (!current.isApproved()) {
        	throw new InvalidTokenException("Invalid refresh token: approval has been revoked");
        }
        
        User user = userService.fetchById(current.getUserId());
        
        Token token = authService.createToken(user, clientId, current.getScope());
        
        token.setApproved(current.isApproved());
        
        if (reuseRefreshToken) {
        	token.setRefreshToken(refreshTokenValue);
        }
        
        try {
    		byte[] data = KClassUtil.serializeObjectAsByteArray(authentication);
    		token.setAuthentication(data);
    	} catch (Exception e) {
    		throw new SystemException(e);
    	}
        
        token = tokenService.update(token);
        
		OAuth2AccessToken t = new AccessToken(
				null, 
				null, 
				token.getAccessToken(),
				token.getRefreshToken(), 
				token.getAccessExpirationDate(), 
				token.getRefreshExpirationDate(), 
				token.getScope());
		
		return t;
	}
    
    /*
	private OAuth2RefreshToken createRefreshToken(OAuth2Authentication authentication) {
		if (!isSupportRefreshToken(authentication.getOAuth2Request())) {
			return null;
		}
		int validitySeconds = getRefreshTokenValiditySeconds(authentication.getOAuth2Request());
		String value = UUID.randomUUID().toString();
		if (validitySeconds > 0) {
			return new DefaultExpiringOAuth2RefreshToken(value, new Date(System.currentTimeMillis()
					+ (validitySeconds * 1000L)));
		}
		return new DefaultOAuth2RefreshToken(value);
	}
    */
	
	/**
	 * Whether to support the refresh token.
	 * 
	 * @param supportRefreshToken Whether to support the refresh token.
	 */
	public void setSupportRefreshToken(boolean supportRefreshToken) {
		this.supportRefreshToken = supportRefreshToken;
	}

	/**
	 * Whether to reuse refresh tokens (until expired).
	 * 
	 * @param reuseRefreshToken Whether to reuse refresh tokens (until expired).
	 */
	public void setReuseRefreshToken(boolean reuseRefreshToken) {
		this.reuseRefreshToken = reuseRefreshToken;
	}

	/**
	 * An authentication manager that will be used (if provided) to check the user authentication when a token is
	 * refreshed.
	 * 
	 * @param authenticationManager the authenticationManager to set
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	 */
	
	/**
	 * Create a refreshed authentication.
	 * 
	 * @param authentication The authentication.
	 * @param request The scope for the refreshed token.
	 * @return The refreshed authentication.
	 * @throws InvalidScopeException If the scope requested is invalid or wider than the original scope.
	 */
	private OAuth2Authentication createRefreshedAuthentication(OAuth2Authentication authentication, TokenRequest request) {
		OAuth2Authentication narrowed = authentication;

		Set<String> scope = request.getScope();

		OAuth2Request clientAuth = authentication.getOAuth2Request().refresh(request);

		if (scope != null && !scope.isEmpty()) {
			Set<String> originalScope = clientAuth.getScope();

			if (originalScope == null || !originalScope.containsAll(scope)) {
				throw new InvalidScopeException("Unable to narrow the scope of the client authentication to " + scope
						+ ".", originalScope);
			}
			else {
				clientAuth = clientAuth.narrowScope(scope);
			}
		}

		narrowed = new OAuth2Authentication(clientAuth, authentication.getUserAuthentication());

		return narrowed;
	}
}
