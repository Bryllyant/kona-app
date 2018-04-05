/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.AppMapper;
import com.bryllyant.kona.app.entity.ApiVersion;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.AppCreds;
import com.bryllyant.kona.app.entity.AppExample;
import com.bryllyant.kona.app.service.ApiVersionService;
import com.bryllyant.kona.app.service.AppCredsService;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.EntityNameRuleService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import com.bryllyant.kona.util.KStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service(AppService.SERVICE_PATH)
public class AppServiceImpl
        extends KAbstractService<App, AppExample, AppMapper>
        implements AppService {

    private static Logger logger = LoggerFactory.getLogger(AppServiceImpl.class);

    @Autowired
    private AppMapper appMapper;

    @Autowired
    private KConfig config;

    @Autowired
    private EntityNameRuleService entityNameRuleService;

    @Autowired
    private AppCredsService appCredsService;

    @Autowired
    private ApiVersionService apiVersionService;

    @Override
    @SuppressWarnings("unchecked")
    protected AppMapper getMapper() {
        return appMapper;
    }

    protected Integer getDefaultAccessTokenTimeout(Long appId) {
        return config.getInteger("app.accessToken.timeout");
    }


    protected Integer getDefaultRefreshTokenTimeout(Long appId) {
        return config.getInteger("app.refreshToken.timeout");
    }

    protected List<String> getDefaultScopeList(Long appId) {
        return config.getList("app.scope.default");
    }

    protected Long toApiVersionId(String version) {
        ApiVersion apiVersion = null;

        if (version != null) {
            apiVersion = apiVersionService.fetchByName(version);
        } else {
            apiVersion = apiVersionService.fetchLatest();
        }

        if (apiVersion == null) {
            throw new IllegalArgumentException("Invalid apiVersion: " + version);
        }

        return apiVersion.getId();
    }

    @Override
    public void validate(App app) {
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

    @Override
    public App getSystemApp() {
        String appSlug = config.getString("system.app.slug");
        logger.debug("appSlug: {}", appSlug);
        return fetchBySlug(appSlug);
    }



    @Override
    public boolean isAppNameAvailable(String name) {
        // check if name starts with punctuation
        //  One of !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~
        if (name.matches("^\\p{Punct}+.*") || name.startsWith("\\s+")) {
            return false;
        }

        // check if any "parts" of the name violate a rule
        name = KInflector.getInstance().slug(name);

        String[] parts = name.split("-");

        for (String part : parts) {
            if (!entityNameRuleService.isAcceptable(part)) {
                return false;
            }
        }

        // finally check if app name already exists
        App app = fetchBySlug(name);

        return (app == null);
    }

    @Override
    public App fetchBySlug(String slug) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

    @Override
    public List<App> fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    @Override  @Transactional
    public App retire(App app) {
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

        appCredsService.expireAppTokens(app.getId());
        return app;
    }

    @Override  @Transactional
    public App create(App app) {
        return create(app, null, null, null, null, null);
    }

    @Override  @Transactional
    public App create(App app, String apiVersion, String redirectUri, String scope) {
        return create(app, apiVersion, redirectUri, scope, null, null);
    }

    @Override  @Transactional
    public App create(App app, String apiVersion, String redirectUri, String scope, String clientId, String clientSecret) {
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
        AppCreds creds = new AppCreds();
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
        creds = appCredsService.add(creds);

        return app;
    }



    @Override  @Transactional
    public App update(App app, String clientId, String apiVersion, String redirectUri, String scope) {
        app = update(app);

        AppCreds creds = appCredsService.fetchByClientId(clientId);
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

        creds = appCredsService.update(creds);

        return app;
    }
}
