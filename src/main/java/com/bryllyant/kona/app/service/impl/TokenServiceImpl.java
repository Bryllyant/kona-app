/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.TokenMapper;
import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.entity.TokenExample;
import com.bryllyant.kona.app.service.KAbstractTokenService;
import com.bryllyant.kona.app.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(TokenService.SERVICE_PATH)
public class TokenServiceImpl 
		extends KAbstractTokenService<Token, TokenExample, TokenMapper>
		implements TokenService {
	
	private static Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

	@Autowired
	private TokenMapper tokenMapper;
	

	
	@Override
	protected boolean entityHasBlobs() {
		return true;
	}
	


	@Override @SuppressWarnings("unchecked")
	protected TokenMapper getMapper() {
		return tokenMapper;
	}


	
	 @Override
    protected TokenExample getEntityExampleObject() { return new TokenExample(); }




    @Override 
    protected void updateCoords(Long tokenId) {
        getMapper().updateCoords(tokenId);
    }



	@Override
	public List<Token> fetchProximate(
			Double latitude,
			Double longitude,
			Double radius,
			Date startDate,
			Date endDate,
			List<Long> objectIdList
	) {
		return getMapper().selectProximate(latitude, longitude, radius, startDate, endDate, objectIdList);
	}
}
