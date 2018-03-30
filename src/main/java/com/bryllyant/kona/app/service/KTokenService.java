/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bryllyant.kona.app.entity.KToken;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

/**
 * The client side stub for the RPC service.
 */
public interface KTokenService<TOKEN extends KToken> extends KService, KEntityService<TOKEN> {
	/**
	 * Return a valid token for the specified accessToken.  
	 * 
	 * Equivalent to fetchByAccessToken(accessToken, false);
	 *
	 * @return A valid Token object or null if the accessToken is not valid.
	 */
	TOKEN fetchByAccessToken(String accessToken);
    
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
	TOKEN fetchByAccessToken(String accessToken, boolean checkValid);

	TOKEN fetchByRefreshToken(String refreshToken);

	TOKEN fetchActiveByClientIdAndUserId(String clientId, Long userId);

	List<TOKEN> fetchActiveByUserId(Long userId);
	
	List<TOKEN> fetchByClientId(String clientId);

	List<TOKEN> fetchByFilter(Map<String,Object> filter);

	TOKEN expire(TOKEN token);
	
	List<TOKEN> expireByClientId(String clientId);

    List<TOKEN> expireByUserId(Long userId);

	/**
	 * Check if a token is valid.  The token will first be refreshed
	 * with the latest attributes before it is checked.
	 */
	boolean isValid(TOKEN token);
    
	boolean isValid(String accessToken);
    

	/**
	 * Check if a token is valid.  Set fetchFreshToken to true to 
	 * first refresh the token's attributes before checking its validity.
	 */
	boolean isValid(TOKEN token, boolean fetchFreshToken);
    
	boolean isValid(String accessToken, boolean fetchFreshToken);
	
    List<TOKEN> fetchProximate(
    		Double latitude,
			Double longitude,
            Double radius,
            Date startDate,
            Date endDate,
            List<Long> objectIdList
    );
}
