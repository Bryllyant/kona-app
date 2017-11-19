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
	
	// ----------------------------------------------------------------------------
	
	@Override
	protected boolean entityHasBlobs() {
		return true;
	}
	
	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected TokenMapper getDao() {
		return tokenDao;
	}

	// ----------------------------------------------------------------------------
	
	@Override
	protected TokenExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
			Map<String, Object> filter, boolean distinct) {
		TokenExample example = new TokenExample();

		if (sortOrder != null) {
			example.setOrderByClause(KMyBatisUtil.getOrderByString(sortOrder));
		}

		if (startRow == null) startRow = 0;
		if (resultSize == null) resultSize = 99999999;

        example.setOffset(startRow);
        example.setLimit(resultSize);
		example.setDistinct(distinct);

		KMyBatisUtil.buildExample(example.or().getClass(), example.or(), filter);
		
		return example;
	}



    @Override 
    protected void updateCoords(Long tokenId) {
        getDao().updateCoords(tokenId);
    }

    // ----------------------------------------------------------------------------

    @Override 
    public List<Token> fetchProximate(Double latitude, Double longitude, Double radius, Date startDate, Date endDate) {
        return getDao().selectProximate(latitude, longitude, radius, startDate, endDate);
    }

}
