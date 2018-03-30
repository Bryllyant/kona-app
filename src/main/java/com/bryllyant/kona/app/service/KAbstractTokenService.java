/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KToken;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractTokenService<TOKEN extends KToken, TOKEN_EXAMPLE extends KEntityExample, MAPPER extends KMyBatisMapper<TOKEN, TOKEN_EXAMPLE>> extends KAbstractService<TOKEN,TOKEN_EXAMPLE,MAPPER>
implements KTokenService<TOKEN> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractTokenService.class);



    protected abstract void updateCoords(Long tokenId); 



    @Override @Transactional
    public TOKEN add(TOKEN token) {
        token = super.add(token);
        updateCoords(token.getId());
        return token;
    }
    


    @Override @Transactional
    public TOKEN update(TOKEN token) {
        token = super.update(token);
        updateCoords(token.getId());
        return token;
    }



    @Override
    public boolean isValid(TOKEN token) {
        return isValid(token, false);
    }



    @Override
    public boolean isValid(String accessToken) {
        return isValid(accessToken, false);
    }



    @Override
    public boolean isValid(String accessToken, boolean fetchFreshToken) {
        TOKEN token = fetchByAccessToken(accessToken);
        return isValid(token, fetchFreshToken);
    }



    @Override
    public boolean isValid(TOKEN token, boolean fetchFreshToken) {

        logger.debug("KAbstractTokenService: isValid: "
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
    public TOKEN fetchByAccessToken(String accessToken) {
        return fetchByAccessToken(accessToken, false);
    }



    @Override
    public TOKEN fetchByAccessToken(String accessToken, boolean checkValid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("accessToken", accessToken);

        TOKEN token = KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));

        if (token != null && checkValid) {
            // NOTE: boolean argument must be FALSE
            if (!isValid(token, false)) {
                token = null;
            }
        }

        return token;
    }



    @Override
    public TOKEN fetchByRefreshToken(String refreshToken) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("refreshToken", refreshToken);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public TOKEN fetchActiveByClientIdAndUserId(String clientId, Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        filter.put("clientId", clientId);
        filter.put("active", true);

        // FIXME: Hardcoding table and column names here is dangerous
        // Also not needed since fetchOne will fail if multiple tokens are retrieved
        //String[] sortOrder = { "core__token.last_updated DESC" };

        TOKEN token = KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));

        // for testing, set isTokenValid(token, false)
        // for production always set isTokenValid(token, true)
        if (token == null || !isValid(token, true)) {
            return null;
        }

        return token;
    }





    @Override
    public List<TOKEN> fetchActiveByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        filter.put("active", true);
        return fetchByCriteria(0, 99999, null, filter, false);
    }



    @Override
    public List<TOKEN> fetchByFilter(Map<String, Object> filter) {
        return fetchByCriteria(0, 99999, null, filter, false);
    }



    @Override
    public List<TOKEN> fetchByClientId(String clientId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("clientId", clientId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }



    @Override
    public TOKEN expire(TOKEN token) {
        token.setActive(false);
        token.setExpiredDate(new Date());
        token = update(token);
        return token;
    }



    @Override
    public List<TOKEN> expireByClientId(String clientId) {
        List<TOKEN> expired = new ArrayList<>();

        List<TOKEN> tokenList = fetchByClientId(clientId);

        for (TOKEN token : tokenList) {
            token = expire(token);
            expired.add(token);
        }

        return expired;
    }



    @Override
    public List<TOKEN> expireByUserId(Long userId) {
        List<TOKEN> expired = new ArrayList<>();

        List<TOKEN> tokens = fetchActiveByUserId(userId);

        for (TOKEN token : tokens) {
            token = expire(token);
            expired.add(token);
        }

        return expired;
    }



    @Override
    public void validate(TOKEN token) {
        if (token.getCreatedDate() == null) {
            token.setCreatedDate(new Date());
        }

        token.setUpdatedDate(new Date());
    }
}
