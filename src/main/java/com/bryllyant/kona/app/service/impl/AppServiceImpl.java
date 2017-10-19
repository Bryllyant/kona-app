/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.dao.AppMapper;
import com.bryllyant.kona.app.entity.ApiVersion;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.AppCreds;
import com.bryllyant.kona.app.entity.AppExample;
import com.bryllyant.kona.app.service.ApiVersionService;
import com.bryllyant.kona.app.service.AppCredsService;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.EntityNameRuleService;
import com.bryllyant.kona.app.service.KAbstractAppService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service(AppService.SERVICE_PATH)
public class AppServiceImpl
        extends KAbstractAppService<App, AppExample, AppCreds>
        implements AppService {

    private static Logger logger = LoggerFactory.getLogger(AppServiceImpl.class);

    @Autowired
    private AppMapper appDao;

    @Autowired
    private KConfig config;

    @Autowired
    private EntityNameRuleService entityNameRuleService;

    @Autowired
    private AppCredsService appCredsService;

    @Autowired
    private ApiVersionService apiVersionService;

    // ----------------------------------------------------------------------------

    @Override
    @SuppressWarnings("unchecked")
    protected AppMapper getDao() {
        return appDao;
    }

    // ----------------------------------------------------------------------------

    @Override
    @SuppressWarnings("unchecked")
    protected AppCredsService getAppCredsService() {
        return appCredsService;
    }

    // ----------------------------------------------------------------------------

    @Override
    protected AppCreds getNewObject() {
        return new AppCreds();
    }

    // ----------------------------------------------------------------------------

    @Override
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

    // ----------------------------------------------------------------------------

    @Override
    protected Integer getDefaultAccessTokenTimeout(Long appId) {
        return config.getInteger("app.accessToken.timeout");
    }

    // ----------------------------------------------------------------------------

    @Override
    protected Integer getDefaultRefreshTokenTimeout(Long appId) {
        return config.getInteger("app.refreshToken.timeout");
    }

    // ----------------------------------------------------------------------------

    @Override
    protected List<String> getDefaultScopeList(Long appId) {
        return config.getList("app.scope.default");
    }

    // ----------------------------------------------------------------------------

    @Override
    protected AppExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
                                                  Map<String, Object> filter, boolean distinct) {
        AppExample example = new AppExample();

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

    // ----------------------------------------------------------------------------

    @Override
    public App getSystemApp() {
        String appSlug = config.getString("system.app.slug");
        logger.debug("appSlug: {}", appSlug);
        return fetchBySlug(appSlug);
    }

    // ----------------------------------------------------------------------------

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
}
