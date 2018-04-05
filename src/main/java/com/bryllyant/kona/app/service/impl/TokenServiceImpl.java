/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.TokenMapper;
import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.entity.TokenExample;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.TokenService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(TokenService.SERVICE_PATH)
public class TokenServiceImpl 
		extends KAbstractService<Token,TokenExample,TokenMapper>
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



    @Override @Transactional
    public Token add(Token token) {
        token = super.add(token);
        updateCoords(token.getId());
        return token;
    }



    @Override @Transactional
    public Token update(Token token) {
        token = super.update(token);
        updateCoords(token.getId());
        return token;
    }


    @Override
    public boolean isValid(Token token) {
        return isValid(token, false);
    }


    @Override
    public boolean isValid(String accessToken) {
        return isValid(accessToken, false);
    }


    @Override
    public boolean isValid(String accessToken, boolean fetchFreshToken) {
        Token token = fetchByAccessToken(accessToken);
        return isValid(token, fetchFreshToken);
    }


    @Override
    public boolean isValid(Token token, boolean fetchFreshToken) {

        logger.debug("TokenServiceImpl: isValid: "
                + "token: {}  fetchFreshToken: {}", token, fetchFreshToken);

        // check if we have a token for this token string
        if (token == null || token.getAccessToken() == null) {
            return false;
        }

        if (fetchFreshToken) {
            // update the token from the database
            token = fetchByAccessToken(token.getAccessToken(), false);
        }

        if (token == null || !token.isActive()) {
            return false;
        }

        // if we don't have an expireDate, then this token
        // never expires, so return true.
        Date d = token.getAccessExpirationDate();

        if (d == null) {
            return true;
        }

        Date now = new Date();
        long nowTime = now.getTime();
        long expireTime = d.getTime();

        // check if this token has expired
        if (nowTime - expireTime >= 0) {
            return false;
        }

        return true;
    }


    /**
     * Return token for specified accessToken.
     *
     * @return A valid Token object or null if the accessToken is not valid.
     */
    @Override
    public Token fetchByAccessToken(String accessToken) {
        return fetchByAccessToken(accessToken, false);
    }



    @Override
    public Token fetchByAccessToken(String accessToken, boolean checkValid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("accessToken", accessToken);

        Token token = KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));

        if (token != null && checkValid) {
            // NOTE: boolean argument must be FALSE
            if (!isValid(token, false)) {
                token = null;
            }
        }

        return token;
    }



    @Override
    public Token fetchByRefreshToken(String refreshToken) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("refreshToken", refreshToken);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public Token fetchActiveByClientIdAndUserId(String clientId, Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        filter.put("clientId", clientId);
        filter.put("active", true);

        // FIXME: Hardcoding table and column names here is dangerous
        // Also not needed since fetchOne will fail if multiple tokens are retrieved
        //String[] sortOrder = { "core__token.last_updated DESC" };

        Token token = KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));

        // for testing, set isTokenValid(token, false)
        // for production always set isTokenValid(token, true)
        if (token == null || !isValid(token, true)) {
            return null;
        }

        return token;
    }



    @Override
    public List<Token> fetchActiveByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        filter.put("active", true);
        return fetchByCriteria(0, 99999, null, filter, false);
    }


    @Override
    public List<Token> fetchByFilter(Map<String, Object> filter) {
        return fetchByCriteria(0, 99999, null, filter, false);
    }


    @Override
    public List<Token> fetchByClientId(String clientId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("clientId", clientId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }


    @Override
    public Token expire(Token token) {
        token.setActive(false);
        token.setExpiredDate(new Date());
        token = update(token);
        return token;
    }


    @Override
    public List<Token> expireByClientId(String clientId) {
        List<Token> expired = new ArrayList<>();

        List<Token> tokenList = fetchByClientId(clientId);

        for (Token token : tokenList) {
            token = expire(token);
            expired.add(token);
        }

        return expired;
    }


    @Override
    public List<Token> expireByUserId(Long userId) {
        List<Token> expired = new ArrayList<>();

        List<Token> tokens = fetchActiveByUserId(userId);

        for (Token token : tokens) {
            token = expire(token);
            expired.add(token);
        }

        return expired;
    }


    @Override
    public void validate(Token token) {
        if (token.getCreatedDate() == null) {
            token.setCreatedDate(new Date());
        }

        token.setUpdatedDate(new Date());

        if (token.getUid() == null) {
            token.setUid(uuid());
        }

    }
}
