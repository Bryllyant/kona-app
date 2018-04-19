/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.api.model.auth.TokenModel;
import com.bryllyant.kona.app.dao.RemoteServiceMapper;
import com.bryllyant.kona.app.entity.RemoteService;
import com.bryllyant.kona.app.entity.RemoteServiceExample;
import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.service.RemoteServiceService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.service.KServiceException;
import com.bryllyant.kona.rest.exception.NotFoundException;
import com.bryllyant.kona.util.AppUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service(RemoteServiceService.SERVICE_PATH)
public class RemoteServiceServiceImpl 
		extends KAbstractService<RemoteService, RemoteServiceExample, RemoteServiceMapper>
        implements RemoteServiceService, com.bryllyant.kona.remote.service.KService, com.bryllyant.kona.data.service.KEntityService<RemoteService> {
	
	private static Logger logger = LoggerFactory.getLogger(RemoteServiceServiceImpl.class);

	@Autowired
	private RemoteServiceMapper remoteServiceMapper;

	@Autowired
    private AppUtil appUtil;


	@Override @SuppressWarnings("unchecked")
	protected RemoteServiceMapper getMapper() {
		return remoteServiceMapper;
	}
    
    @Override
    public void validate(RemoteService remoteService) {
        if (remoteService.getCreatedDate() == null) {
            remoteService.setCreatedDate(new Date());
        }

        remoteService.setUpdatedDate(new Date());

        if (remoteService.getName() != null) {
            String slug = KInflector.getInstance().slug(remoteService.getName());
            remoteService.setSlug(slug);
        }

        if (remoteService.getUid() == null) {
            remoteService.setUid(uuid());
        }
    }

    @Override
    public RemoteService fetchBySlug(String slug) {
        slug = slug.trim().toLowerCase();
        Map<String,Object> filter = KMyBatisUtil.createFilter("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0,9999, null, filter,  false));
    }

    @Override
    public TokenModel exchangeToken(RemoteService service, String code) {
        Map<String,Object> request = new HashMap<>();
        request.put("grant_type", "authorization_code");
        request.put("code", code);
        request.put("client_id", service.getClientId());
        request.put("client_secret", service.getClientSecret());
        request.put("redirect_uri", service.getRedirectUri());

        try {
            RestTemplate template = new RestTemplate();

            Map<String,Object> result = template.postForObject(service.getTokenUri(), request, Map.class);

            if (result == null) {
                throw new NotFoundException("Access Token not found for code: " + code);
            }

            return toToken(result);

        } catch (RestClientException e) {
            throw new KServiceException(e);
        }
    }

    private TokenModel toToken(Map<String,Object> map) {
        Token.Type tokenType = null;

        String tt = appUtil.getStringValue(map.get("token_type"));

        if (tt != null)  {
            if (tt.equalsIgnoreCase(Token.Type.BEARER.name())) {
                tokenType = Token.Type.BEARER;
            } else if (tt.equalsIgnoreCase(Token.Type.BASIC.name())) {
                tokenType = Token.Type.BASIC;
            }
        }

        TokenModel token = new TokenModel();

        token.setScope(appUtil.getStringValue(map.get("scope")));
        token.setAccessToken(appUtil.getStringValue(map.get("access_token")));
        token.setRefreshToken(appUtil.getStringValue(map.get("refresh_token")));
        token.setTokenType(tokenType);
        token.setExpiresIn(appUtil.getIntegerValue(map.get("expires_in")));

        return token;
    }
}
