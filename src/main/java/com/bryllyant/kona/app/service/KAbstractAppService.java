/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KApp;
import com.bryllyant.kona.app.entity.KAppCreds;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import com.bryllyant.kona.util.KStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractAppService<APP extends KApp, APP_EXAMPLE extends KEntityExample, MAPPER extends KMyBatisMapper<APP, APP_EXAMPLE>,APP_CREDS extends KAppCreds>
		extends KAbstractService<APP,APP_EXAMPLE,MAPPER>
		implements KAppService<APP> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractAppService.class);

	protected abstract APP_CREDS getNewObject();

	// Returns latest apiVersion if version is null
    protected abstract Long toApiVersionId(String version);

    protected abstract Integer getDefaultAccessTokenTimeout(Long appId);
    
    protected abstract Integer getDefaultRefreshTokenTimeout(Long appId);
    
    protected abstract List<String> getDefaultScopeList(Long appId);
	
	protected abstract <S extends KAppCredsService<APP_CREDS>> S getAppCredsService();
	

	@Override
	public APP fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

	
	@Override
	public APP fetchBySlug(String slug) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

	@Override
	public List<APP> fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(0, 99999, null, filter, false);
	}
	
    @Override  @Transactional
    public APP retire(APP app) {
        // fetch fresh object
        app = fetchById(app.getId());

        // this isn't a slip up in logic. it's possible that the app object passed in
        // is not retired but by the time this call is made and the object is refreshed
        // is has been retired.  if so, fetchById should return null.
        // return app if already retired
        if (app == null || app.getDeletedDate() != null) {
            return app;
        }

        // NOTE: we need uuid here in case multiple apps with the same name are deleted.
        // first app called test is deleted, then second app created called test gets deleted.
        String prefix = "$RETIRED_" + uuid() + "_";
        app.setSlug(prefix + app.getSlug());;
        app.setName(prefix + app.getName());;
        app.setEnabled(false);
        app.setDeletedDate(new Date());
        app = update(app);

        getAppCredsService().expireAppTokens(app.getId());
        return app;
    }
    
    @Override  @Transactional
    public APP create(APP app) {
        return create(app, null, null, null, null, null);
    }

	@Override  @Transactional
	public APP create(APP app, String apiVersion, String redirectUri, String scope) {
		return create(app, apiVersion, redirectUri, scope, null, null);
	}
	
	@Override  @Transactional
	public APP create(APP app, String apiVersion, String redirectUri, String scope, String clientId, String clientSecret) {
		app = add(app);

		if (clientId == null) {
			clientId = uuid();
		}

		if (clientSecret == null) {
			clientSecret = uuid();
		}

		if (scope == null) {
			List<String> scopes = getDefaultScopeList(app.getId());

			scope = KStringUtil.toCommaList(scopes);
			logger.debug("scope is null; setting to default: " + scope);
		}

		// create creds
		APP_CREDS creds = getNewObject();
		creds.setAppId(app.getId());
		creds.setApiVersionId(toApiVersionId(apiVersion));
		creds.setClientId(clientId);
		creds.setClientSecret(clientSecret);
		creds.setRedirectUri(redirectUri);
		creds.setScope(scope);
		creds.setEnabled(true);
		creds.setAccessTokenTimeout(getDefaultAccessTokenTimeout(app.getId()));
		creds.setRefreshTokenTimeout(getDefaultRefreshTokenTimeout(app.getId()));
		creds.setCreatedDate(new Date());
		creds = getAppCredsService().add(creds);

		return app;
	}


	
	@Override  @Transactional
	public APP update(APP app, String clientId, String apiVersion, String redirectUri, String scope) {
		app = update(app);

		APP_CREDS creds = getAppCredsService().fetchByClientId(clientId);
		if (!creds.getAppId().equals(app.getId())) {
			throw new IllegalArgumentException("App clientId mismatch: "
					+ "\nappId: " + app.getId() 
					+ "clientId: " + clientId);
		}

		if (apiVersion != null) {
			creds.setApiVersionId(toApiVersionId(apiVersion));
		}

		if (redirectUri != null) {
			creds.setRedirectUri(redirectUri);
		}

		if (scope != null) {
			creds.setScope(scope);
		}
        
		creds = getAppCredsService().update(creds);

		return app;
	}
	

	
	@Override
	public void validate(APP app) {
    	if (app.getCreatedDate() == null) {
			app.setCreatedDate(new Date());
		}
    	
    	app.setUpdatedDate(new Date());
        
    	if (app.getSlug() == null && app.getName() != null)  {
    		String slug = KInflector.getInstance().slug(app.getName());
            app.setSlug(slug);
    	}
        
    	if (app.getUid() == null) {
    		app.setUid(uuid());
    	}
	}
}
