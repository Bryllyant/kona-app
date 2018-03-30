/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KAppCreds;
import com.bryllyant.kona.app.entity.KToken;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.app.entity.KUserAuth;
import com.bryllyant.kona.app.util.KUtil;
import com.bryllyant.kona.remote.service.KServiceClient;
import com.bryllyant.kona.util.KDateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public abstract class KAbstractAuthService<USER extends KUser, USER_AUTH extends KUserAuth,
											TOKEN extends KToken, APP_CREDS extends KAppCreds>
		implements KAuthService<USER, TOKEN> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractAuthService.class);
    
    protected abstract <S extends KTokenService<TOKEN>> S getTokenService();
    
    protected abstract <S extends KUserService<USER>> S getUserService();
    
    protected abstract <S extends KUserAuthService<USER_AUTH,USER>> S getUserAuthService();
    
    protected abstract <S extends KAppCredsService<APP_CREDS>> S getAppCredsService();



    protected abstract TOKEN getNewObject();
    

    protected String generateToken() {
    	return KUtil.uuid();
    }
    

    
    /**
     * Login the user and return a token.
     *  - If the credentials match, a token is returned; if not, null is returned.
     */
    @Override 
    public TOKEN login(String clientId, String username, String password) {
    	if (username == null) {
    		throw new KAuthException("Username is null");
    	}

    	// Get the user
    	USER user = null;
    	
    	try {
    		user = getUserAuthService().validateCredentials(username, password);
    	} catch (KAuthException e) {
    		logger.debug(e.getMessage(), e);
    	}
        
        return login(clientId, user);
    }
    

    
    @Override 
    public TOKEN login(String clientId, USER user) {

    	// see if we have an active token for this user and app
    	TOKEN activeToken = getTokenService().fetchActiveByClientIdAndUserId(clientId, user.getId());
    	if (activeToken != null) {

    		Long accessCount = activeToken.getAccessCount();
    		if (accessCount == null) {
    			accessCount = 1L;
    		} else {
    			accessCount += 1;
    		}
    		activeToken.setAccessCount(accessCount);
    		activeToken.setLastLoginDate(activeToken.getLoginDate());
    		activeToken.setLoginDate(new Date());

    		logger.debug("Active Token:\n" + activeToken);

    		getTokenService().update(activeToken);
            
    		loginUser(user, activeToken);
            
    		return activeToken;
    	}

    	TOKEN token = createToken(user, clientId, getClientDefaultScope(clientId));
        
    	loginUser(user, token);

    	return token;
    }
    

    
    @Override
    public TOKEN createToken(USER user, String clientId) {
    	return createToken(user, clientId, null);
    }
    

    
    @Override
    public TOKEN createToken(USER user, String clientId, String scope) {
    	String accessToken = generateToken();
    	String refreshToken = generateToken();
        
    	Date now = new Date();
        Date accessExpiration = null;
        Date refreshExpiration = null;
        
        if (scope == null) {
        	scope = getClientDefaultScope(clientId);
        }
        
        Integer timeout = getClientAccessTokenTimeout(clientId);
        if (timeout != null && timeout>0) {
            accessExpiration = KDateUtil.addSecs(new Date(), timeout);
        }
        
        timeout = getClientRefreshTokenTimeout(clientId);
        if (timeout != null && timeout>0) {
            refreshExpiration = KDateUtil.addSecs(new Date(), timeout);
        }

        TOKEN token = getNewObject();
        token.setAccessCount(1L);
        token.setCreatedDate(now);
        token.setAppClientId(clientId);
        token.setAppId(getAppId(clientId));
        token.setUserId(user.getId());
    	token.setType(getLoginTokenType());
    	token.setAccessToken(accessToken);
    	token.setRefreshToken(refreshToken);
    	token.setScope(scope);
    	token.setUsername(user.getUsername());
    	token.setLastLoginDate(null);
    	token.setLoginDate(now);
    	token.setAccessExpirationDate(accessExpiration);
    	token.setRefreshExpirationDate(refreshExpiration);
    	token.setActive(true);

    	return getTokenService().add(token);
    }
    


    private void loginUser(USER user, TOKEN token) {
        if (user == null) return;

        Date loginDate = user.getLoginDate();

        if (loginDate != null) {
            user.setLastLoginDate(loginDate);
        }

        user.setLoginDate(new Date());

        user.setPresence(KUser.Presence.ONLINE);

        getUserService().update(user);
    }
    

    
    @Override 
    public TOKEN login(String accessToken, KServiceClient client) {
        TOKEN token = getTokenService().fetchByAccessToken(accessToken, false);
        return login(token, client);
    }
    

    
    @Override 
    public TOKEN login(TOKEN token, KServiceClient client) {
        if (token == null) {
        	return null;
        }
        
        if (!getTokenService().isValid(token, true)) {
        	return null;
        }

        Date now = new Date();
        token.setLastLoginDate(token.getLoginDate());
        token.setLoginDate(now);
        
        if (client != null) {
        	token.setHostname(client.getHostname());
        	token.setUserAgent(client.getUserAgent());
            token.setLatitude(client.getLatitude());
            token.setLongitude(client.getLongitude());
            token.setAppVersion(client.getAppVersion());
            token.setAppBuild(client.getAppBuild());
        }
        
        token = getTokenService().update(token);

        USER user = getUserService().fetchById(token.getUserId());
        
        loginUser(user, token);
        
        return token;
    }


    
    private void logoutUser(TOKEN token) {
        if (token == null || token.getUserId() == null) return;

        USER user = getUserService().fetchById(token.getUserId());
        
        if (user == null) return;

        Date loginDate = user.getLoginDate();

        if (loginDate != null) {
            user.setLastLoginDate(loginDate);
        }
        
        user.setLoginDate(null);
        
        user.setPresence(KUser.Presence.OFFLINE);

        getUserService().update(user);
    }
    


    @Override 
    public void logout(TOKEN token) {
        if (token == null) return;
        logger.debug("logout(token) called: token: " + token.getAccessToken());

        token.setLogoutDate(new Date());
        token.setActive(false);

        getTokenService().update(token);
        logoutUser(token);
    }
    

    
    @Override 
    public void logout(String accessToken) {
        TOKEN token = getTokenService().fetchByAccessToken(accessToken);
        logout(token);
    }
    

    
    @Override
    public void logout(USER user) {
        if (user == null) return;
    	List<TOKEN> tokenList = getTokenService().fetchActiveByUserId(user.getId());
    	for (TOKEN token : tokenList) {
    		logout(token);
    	}
    }
    

    
    /** Number of seconds before AccessToken expires. */ 
    protected Integer getClientAccessTokenTimeout(String clientId) {
    	APP_CREDS creds = getAppCredsService().fetchByClientId(clientId);
    	return creds.getAccessTokenTimeout();
    }
    

    
    /** Number of seconds before RefreshToken expires. */
    protected Integer getClientRefreshTokenTimeout(String clientId) {
    	APP_CREDS creds = getAppCredsService().fetchByClientId(clientId);
    	return creds.getRefreshTokenTimeout();
    }
    

    
    /** Default scope assigned to this client */
    protected String getClientDefaultScope(String clientId) {
    	APP_CREDS creds = getAppCredsService().fetchByClientId(clientId);
    	return creds.getScope();
    }


    
    protected Long getAppId(String clientId) {
    	APP_CREDS creds = getAppCredsService().fetchByClientId(clientId);
    	return creds.getAppId();
    }
    

   
}
