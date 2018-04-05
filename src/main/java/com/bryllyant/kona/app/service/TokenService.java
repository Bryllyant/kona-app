/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TokenService extends KService, KEntityService<Token> {
	String SERVICE_PATH = "rpc/TokenService";

    /**
     * Return a valid token for the specified accessToken.
     *
     * Equivalent to fetchByAccessToken(accessToken, false);
     *
     * @return A valid Token object or null if the accessToken is not valid.
     */
    Token fetchByAccessToken(String accessToken);

    /**
     * Return a token for the specified accessToken.
     *
     * If checkValid is true, first run a check to see if the token
     * is currently valid.  If not, return null.
     *
     * If checkValid is false, this method will return a Token
     * if one is found for this accessToken.  It's up to the calling
     * program to check if the returned token is active and
     * has not expired
     *
     * @return A valid Token object or null if the accessToken is not valid.
     */
    Token fetchByAccessToken(String accessToken, boolean checkValid);

    Token fetchByRefreshToken(String refreshToken);

    Token fetchActiveByClientIdAndUserId(String clientId, Long userId);

    List<Token> fetchActiveByUserId(Long userId);

    List<Token> fetchByClientId(String clientId);

    List<Token> fetchByFilter(Map<String,Object> filter);

    Token expire(Token token);

    List<Token> expireByClientId(String clientId);

    List<Token> expireByUserId(Long userId);

    /**
     * Check if a token is valid.  The token will first be refreshed
     * with the latest attributes before it is checked.
     */
    boolean isValid(Token token);

    boolean isValid(String accessToken);


    /**
     * Check if a token is valid.  Set fetchFreshToken to true to
     * first refresh the token's attributes before checking its validity.
     */
    boolean isValid(Token token, boolean fetchFreshToken);

    boolean isValid(String accessToken, boolean fetchFreshToken);

    List<Token> fetchProximate(
            Double latitude,
            Double longitude,
            Double radius,
            Date startDate,
            Date endDate,
            List<Long> objectIdList
    );
}
