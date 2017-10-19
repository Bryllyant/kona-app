/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.api.security.oauth2;

import com.bryllyant.kona.app.api.security.token.AccessToken;
import com.bryllyant.kona.app.api.security.user.UserSpringAuthService;
import com.bryllyant.kona.app.api.service.ApiAuthService;
import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.rest.exception.SystemException;
import com.bryllyant.kona.util.KClassUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * TokenStore. 
 * 
 * Modified from version created by Iain Porter.
 */
public class OAuth2TokenStore implements TokenStore {
	private static Logger logger = Logger.getLogger(OAuth2TokenStore.class);

    @Autowired
    private ApiAuthService apiAuthService;
    
    @Autowired
	private UserSpringAuthService userSpringAuthService;
    
    // ----------------------------------------------------------------------------

    private Collection<OAuth2AccessToken> extractAccessTokens(List<Token> tokens) {
        List<OAuth2AccessToken> accessTokens = new ArrayList<OAuth2AccessToken>();
        for (Token token : tokens) {
        	OAuth2AccessToken t = toOAuth2AccessToken(token);
            if (t != null) {
            	accessTokens.add(toOAuth2AccessToken(token));
            }
        }
        return accessTokens;
    }
    
    // ----------------------------------------------------------------------------

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
		List<Token> tokens = apiAuthService.fetchTokensByClientId(clientId);
        return extractAccessTokens(tokens);
    }
    
    // ----------------------------------------------------------------------------

    @Override
    // In our use case, userName is the tokenKey so we should only ever get 1 result.
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
    	List<OAuth2AccessToken> tokens = new ArrayList<OAuth2AccessToken>();
    	
    	Token t = fetchTokenByAccessToken(userName);
    	
    	if (t != null) {  
    		if (!t.getAppClientId().equals(clientId)) {
    			throw new IllegalStateException("Invalid clientId: " +  clientId + " for username: " + userName);
    		}
    		
    		OAuth2AccessToken token = toOAuth2AccessToken(t);
    		
    		if (token != null) {
    			tokens.add(token);
    		}
    	}
    	
    	return tokens;
    }
    
    // ----------------------------------------------------------------------------
    
	@Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        if (authentication == null) return null;
        
        logger.debug("OAuth2TokenStore.getAccessToken: called for authentication: " + KClassUtil.toString(authentication));
        Authentication userAuth = authentication.getUserAuthentication();
        
        logger.debug("OAuth2TokenStore.getAccessToken: user authentication: " + KClassUtil.toString(userAuth));
        
        if (userAuth == null) {
        	throw new IllegalStateException("User authentication is null for OAuth2Authentication object");
        }
        
    	User user = (User) authentication.getUserAuthentication().getPrincipal();
        
    	logger.debug("OAuth2TokenStore.getAccessToken: UserDetails object: " + KClassUtil.toString(user));
        
    	if (user == null) {
        	throw new IllegalStateException("User is null for User authentication object");
    	}
        
        AccessToken token = (AccessToken) readAccessToken(user.getUsername());
        
    	Map<String,String> params = authentication.getOAuth2Request().getRequestParameters(); 
    	
		for (String key : params.keySet()) { 
			token.addInfo(key, params.get(key)); 
		}
		
        return token;
    }
	
	// ----------------------------------------------------------------------------

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
    	
        Token token = apiAuthService.fetchTokenByAccessToken(tokenValue);
        if (token == null) {
            return null; //let spring security handle the invalid token
        }
        
        return toOAuth2AccessToken(token);
    }
    
	// ----------------------------------------------------------------------------

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        return readAuthentication(token.getValue());
    }
    
	// ----------------------------------------------------------------------------
    
    private Token fetchTokenByAccessToken(String accessToken) {
        Token token = apiAuthService.fetchTokenByAccessToken(accessToken, false);
        return validateToken(token);
    }
    
	// ----------------------------------------------------------------------------
    
    private Token fetchTokenByRefreshToken(String refreshToken) {
        Token token = apiAuthService.fetchTokenByRefreshToken(refreshToken);
        return validateToken(token);
    }
    
	// ----------------------------------------------------------------------------
    
    private Token validateToken(Token token) {
        if (token == null || !token.isActive()) {
        	return null;
        }
        
        if (AccessToken.isExpired(token)) {
        	token.setActive(false);
        	apiAuthService.updateToken(token);
            return null;
        }
        
        return token;
    }
    
	// ----------------------------------------------------------------------------

    @Override
    public OAuth2Authentication readAuthentication(String accessToken) {
        if (accessToken == null) return null;
        
        Token token = fetchTokenByAccessToken(accessToken);
        if (token == null) return null;
        
        byte[] data = token.getAuthentication();
        
        // if authentication is not available, then this token is bad
        if (data == null) {
        	token.setActive(false);
            token.setRetiredDate(new Date());
            apiAuthService.updateToken(token);
            return null;
        }
        
        try {
        	return KClassUtil.deserializeObjectFromByteArray(data);
        } catch (Exception e) {
        	throw new SystemException(e);
        }
    }
    
	// ----------------------------------------------------------------------------

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
        if (token == null) return null;
        
        Token t = fetchTokenByRefreshToken(token.getValue());
        if (t == null) return null;
        
        
        byte[] data = t.getAuthentication();
        try {
        	return KClassUtil.deserializeObjectFromByteArray(data);
        } catch (Exception e) {
        	throw new SystemException(e);
        }
    }
    
	// ----------------------------------------------------------------------------

    @Override
    public OAuth2RefreshToken readRefreshToken(String tokenValue) {
        Token token = apiAuthService.fetchTokenByRefreshToken(tokenValue);
        
        token = validateToken(token);
        
        if (token == null) return null;
        
        OAuth2AccessToken t = toOAuth2AccessToken(token);
        
        if (t == null) return null;
        
        return t.getRefreshToken();
    }
    
	// ----------------------------------------------------------------------------

    @Override
    public void removeAccessToken(OAuth2AccessToken token) {
        Token t = apiAuthService.fetchTokenByAccessToken(token.getValue(), false);
        
        if (t != null) {
        	t.setActive(false);
        	t.setRetiredDate(new Date());
        	apiAuthService.updateToken(t);
        }
    }
    
	// ----------------------------------------------------------------------------

    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
        removeRefreshToken(refreshToken);
    }
    
	// ----------------------------------------------------------------------------

    @Override
    // NOTE: removing the refresh token invalidates the entire token
    public void removeRefreshToken(OAuth2RefreshToken token) {
        Token t = apiAuthService.fetchTokenByRefreshToken(token.getValue());
        
        if (t != null) {
        	t.setActive(false);
        	t.setRetiredDate(new Date());
        	apiAuthService.updateToken(t);
        }
    }
    
	// ----------------------------------------------------------------------------

    @Override
    // This token should already be stored in the database since it was generated
    // by AuthService.login().  We just need to store the associated authentication object
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
    	//AccessToken accessToken = (AccessToken)token;
    	if (token == null || authentication == null) return;

    	try {
    		byte[] data = KClassUtil.serializeObjectAsByteArray(authentication);
    		Token t = fetchTokenByAccessToken(token.getValue());
    		
    		if (t != null) {
    			t.setAuthentication(data);
    			apiAuthService.updateToken(t);
    		}
    	} catch (Exception e) {
    		throw new SystemException(e);
    	}
    }
    
	// ----------------------------------------------------------------------------

    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
    	if (refreshToken == null || authentication == null) return;
    	
    	try {
    		byte[] data = KClassUtil.serializeObjectAsByteArray(authentication);
    		Token t = apiAuthService.fetchTokenByRefreshToken(refreshToken.getValue());
    		
            if (t != null) {
            	t.setAuthentication(data);
            	apiAuthService.updateToken(t);
            }
    	} catch (Exception e) {
    		throw new SystemException(e);
    	}
    }
    
	// ----------------------------------------------------------------------------

    private OAuth2AccessToken toOAuth2AccessToken(Token t) {
        if (t == null) return null;
        
		UserDetails userDetails = userSpringAuthService.loadUserByUsername(t.getAccessToken());
        if (userDetails == null) return null;
        
        OAuth2AccessToken token = new AccessToken(userDetails, userDetails.getAuthorities(), t.getAccessToken(),
				t.getRefreshToken(), t.getAccessExpirationDate(), t.getRefreshExpirationDate(), t.getScope());
        
		logger.debug("toOAuth2AccessToken called for token\n" + t 
				+ "---- converted to AccessToken ---\n" + KClassUtil.toString(token));
        
		return token;
	}

}
