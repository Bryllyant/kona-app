/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.entity.AppCreds;
import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AppCredsService;
import com.bryllyant.kona.app.service.AuthService;
import com.bryllyant.kona.app.service.AuthException;
import com.bryllyant.kona.app.service.TokenService;
import com.bryllyant.kona.app.service.UserAuthService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.remote.service.KServiceClient;
import com.bryllyant.kona.util.AppUtil;
import com.bryllyant.kona.util.KDateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(AuthService.SERVICE_PATH)
public class AuthServiceImpl implements AuthService {
	
	private static Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserAuthService userAuthService;
	
	@Autowired
	private AppCredsService appCredsService;

    @Autowired
    private AppUtil appUtil;


    protected String generateToken() {
        return appUtil.uuid();
    }

    @Override
	public Token.Type getLoginTokenType() {
		return Token.Type.BEARER;
	}

    /**
     * Login the user and return a token.
     *  - If the credentials match, a token is returned; if not, null is returned.
     */
    @Override
    public Token login(String clientId, String username, String password) {
        if (username == null) {
            throw new AuthException("Username is null");
        }

        // Get the user
        User user = null;

        try {
            user = userAuthService.validateCredentials(username, password);
        } catch (AuthException e) {
            logger.debug(e.getMessage(), e);
        }

        return login(clientId, user);
    }



    @Override
    public Token login(String clientId, User user) {

        // see if we have an active token for this user and app
        Token activeToken = tokenService.fetchActiveByClientIdAndUserId(clientId, user.getId());
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

            tokenService.update(activeToken);

            loginUser(user, activeToken);

            return activeToken;
        }

        Token token = createToken(user, clientId, getClientDefaultScope(clientId));

        loginUser(user, token);

        return token;
    }



    @Override
    public Token createToken(User user, String clientId) {
        return createToken(user, clientId, null);
    }


    @Override
    public Token createToken(User user, String clientId, String scope) {
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

        Token token = new Token();
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

        return tokenService.add(token);
    }



    private void loginUser(User user, Token token) {
        if (user == null) return;

        user.setLastLoginDate(new Date());

        user.setPresence(User.Presence.ONLINE);

        userService.update(user);
    }



    @Override
    public Token login(String accessToken, KServiceClient client) {
        Token token = tokenService.fetchByAccessToken(accessToken, false);
        return login(token, client);
    }



    @Override
    public Token login(Token token, KServiceClient client) {
        if (token == null) {
            return null;
        }

        if (!tokenService.isValid(token, true)) {
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

        token = tokenService.update(token);

        User user = userService.fetchById(token.getUserId());

        loginUser(user, token);

        return token;
    }



    private void logoutUser(Token token) {
        if (token == null || token.getUserId() == null) return;

        User user = userService.fetchById(token.getUserId());

        if (user == null) return;

        user.setPresence(User.Presence.OFFLINE);

        userService.update(user);
    }



    @Override
    public void logout(Token token) {
        if (token == null) return;
        logger.debug("logout(token) called: token: " + token.getAccessToken());

        token.setLogoutDate(new Date());
        token.setActive(false);

        tokenService.update(token);
        logoutUser(token);
    }



    @Override
    public void logout(String accessToken) {
        Token token = tokenService.fetchByAccessToken(accessToken);
        logout(token);
    }



    @Override
    public void logout(User user) {
        if (user == null) return;
        List<Token> tokenList = tokenService.fetchActiveByUserId(user.getId());
        for (Token token : tokenList) {
            logout(token);
        }
    }



    /** Number of seconds before AccessToken expires. */
    protected Integer getClientAccessTokenTimeout(String clientId) {
        AppCreds creds = appCredsService.fetchByClientId(clientId);
        return creds.getAccessTokenTimeout();
    }



    /** Number of seconds before RefreshToken expires. */
    protected Integer getClientRefreshTokenTimeout(String clientId) {
        AppCreds creds = appCredsService.fetchByClientId(clientId);
        return creds.getRefreshTokenTimeout();
    }



    /** Default scope assigned to this client */
    protected String getClientDefaultScope(String clientId) {
        AppCreds creds = appCredsService.fetchByClientId(clientId);
        return creds.getScope();
    }



    protected Long getAppId(String clientId) {
        AppCreds creds = appCredsService.fetchByClientId(clientId);
        return creds.getAppId();
    }



}
