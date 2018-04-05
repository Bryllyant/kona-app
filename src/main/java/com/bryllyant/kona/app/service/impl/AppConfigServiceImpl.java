/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.AppConfigMapper;
import com.bryllyant.kona.app.entity.AppConfig;
import com.bryllyant.kona.app.entity.AppConfigExample;
import com.bryllyant.kona.app.service.AppConfigService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(AppConfigService.SERVICE_PATH)
public class AppConfigServiceImpl
        extends KAbstractService<AppConfig, AppConfigExample, AppConfigMapper>
        implements AppConfigService {

    private static Logger logger = LoggerFactory.getLogger(AppConfigServiceImpl.class);

    @Autowired
    private AppConfigMapper appConfigMapper;

    @Override
    @SuppressWarnings("unchecked")
    protected AppConfigMapper getMapper() {
        return appConfigMapper;
    }


    @Override
    public void validate(AppConfig appConfig) {
        if (appConfig.getCreatedDate() == null) {
            appConfig.setCreatedDate(new Date());
        }

        if (appConfig.getUid() == null) {
            appConfig.setUid(uuid());
        }

        appConfig.setUpdatedDate(new Date());
    }



    @Override
    public List<AppConfig> fetchByAppIdAndEnv(Long appId, AppConfig.Env env) {
        Map<String,Object> filter = KMyBatisUtil.filter()
                .and("appId", appId)
                .and("env", env)
                .build();

        return fetchByCriteria(filter);
    }

    public AppConfig fetchGlobalAppValue(Long appId, String name) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
        filter.put("env", null);
        filter.put("name", name);

        List<AppConfig> result = fetchByCriteria(filter);
        if (result != null && result.size() == 1) {
            return KMyBatisUtil.fetchOne(result);
        }

        return null;
    }

    public AppConfig fetchGlobalEnvValue(AppConfig.Env env, String name) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("appId", null);
        filter.put("env", env);
        filter.put("name", name);

        List<AppConfig> result = fetchByCriteria(filter);
        if (result != null && result.size() == 1) {
            return KMyBatisUtil.fetchOne(result);
        }

        return null;
    }

    public AppConfig fetchGlobalValue(String name) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("appId", null);
        filter.put("env", null);
        filter.put("name", name);

        List<AppConfig> result = fetchByCriteria(filter);
        if (result != null && result.size() == 1) {
            return KMyBatisUtil.fetchOne(result);
        }

        return null;
    }



    @Override
    public AppConfig fetchByAppIdAndEnvAndName(Long appId, AppConfig.Env env, String name) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
        filter.put("env", env);
        filter.put("name", name);

        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }



    @Override
    public Map<String,Object> getConfig(Long appId, AppConfig.Env env) {
        Map<String,Object> map = new HashMap<>();

        // get global vars
        List<AppConfig> globalList = fetchByAppIdAndEnv(null,  null);

        for (AppConfig config : globalList) {
            map.put(config.getName(), toValue(config.getValue()));
        }

        if (appId != null) {
            // get app global vars
            List<AppConfig> configList = fetchByAppIdAndEnv(appId,  null);

            for (AppConfig config : configList) {
                map.put(config.getName(), toValue(config.getValue()));
            }
        }

        if (env != null) {
            // get env global vars
            List<AppConfig> configList = fetchByAppIdAndEnv(null,  env);

            for (AppConfig config : configList) {
                map.put(config.getName(), toValue(config.getValue()));
            }
        }

        // next get environment specific params
        if (appId != null && env != null) {
            List<AppConfig> configList  = fetchByAppIdAndEnv(appId, env);

            for (AppConfig config : configList) {
                map.put(config.getName(), toValue(config.getValue()));
            }
        }

        return map;
    }

    private Object toValue(String json) {
        if (json == null) return null;

        Object value = KJsonUtil.fromJson(json);

        return value;
    }


    @Override @Transactional
    // Note this method is additive.  It does not remove entries not defined in Map.
    public  Map<String,Object> save(Long appId, AppConfig.Env env, Map<String, Object> map) {
        for (String key : map.keySet()) {

            AppConfig appConfig = fetchByAppIdAndEnvAndName(appId, env, key);

            if (appConfig == null) {
                appConfig = new AppConfig();
                appConfig.setAppId(appId);
                appConfig.setEnv(env);
                appConfig.setName(key);
                appConfig.setCreatedDate(new Date());
            }

            appConfig.setValue(KJsonUtil.toJson(map.get(key)));

            save(appConfig);
        }

        return getConfig(appId, env);
    }

    @Override @Transactional
    public  Map<String,Object> remove(Long appId, AppConfig.Env env, List<String> keyList) {
        for (String key : keyList) {
            AppConfig appConfig = fetchByAppIdAndEnvAndName(appId, env, key);
            remove(appConfig);
        }

        return getConfig(appId, env);
    }


}
