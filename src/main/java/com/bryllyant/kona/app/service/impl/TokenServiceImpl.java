/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.TokenMapper;
import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.entity.TokenExample;
import com.bryllyant.kona.app.service.KAbstractTokenService;
import com.bryllyant.kona.app.service.TokenService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(TokenService.SERVICE_PATH)
public class TokenServiceImpl 
		extends KAbstractTokenService<Token,TokenExample> 
		implements TokenService {
	
	private static Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

	@Autowired
	private TokenMapper tokenDao;
	

	
	@Override
	protected boolean entityHasBlobs() {
		return true;
	}
	


	@Override @SuppressWarnings("unchecked")
	protected TokenMapper getDao() {
		return tokenDao;
	}


	
	 @Override
    protected TokenExample getEntityExampleObject() { return new TokenExample(); }




    @Override 
    protected void updateCoords(Long tokenId) {
        getDao().updateCoords(tokenId);
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
		return getDao().selectProximate(latitude, longitude, radius, startDate, endDate, objectIdList);
	}
}
