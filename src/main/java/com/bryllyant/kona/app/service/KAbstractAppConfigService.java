/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KAppConfig;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class KAbstractAppConfigService<APP_CONFIG extends KAppConfig, APP_CONFIG_EXAMPLE extends KEntityExample, MAPPER extends KMyBatisMapper<APP_CONFIG, APP_CONFIG_EXAMPLE>> extends KAbstractService<APP_CONFIG,APP_CONFIG_EXAMPLE,MAPPER>
implements KAppConfigService<APP_CONFIG> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractAppConfigService.class);

    protected abstract APP_CONFIG getNewObject();


	@Override
	public void validate(APP_CONFIG appConfig) {
		if (appConfig.getCreatedDate() == null) {
			appConfig.setCreatedDate(new Date());
		}

		if (appConfig.getUid() == null) {
			appConfig.setUid(uuid());
		}

		appConfig.setUpdatedDate(new Date());
	}



    @Override
    public List<APP_CONFIG> fetchByAppIdAndEnv(Long appId, KAppConfig.Env env) {
        Map<String,Object> filter = KMyBatisUtil.filter()
                .and("appId", appId)
                .and("env", env)
                .build();

        return fetchByCriteria(filter);
    }

    public APP_CONFIG fetchGlobalAppValue(Long appId, String name) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
        filter.put("env", null);
        filter.put("name", name);

        List<APP_CONFIG> result = fetchByCriteria(filter);
        if (result != null && result.size() == 1) {
            return KMyBatisUtil.fetchOne(result);
        }

        return null;
    }

    public APP_CONFIG fetchGlobalEnvValue(KAppConfig.Env env, String name) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("appId", null);
        filter.put("env", env);
        filter.put("name", name);

        List<APP_CONFIG> result = fetchByCriteria(filter);
        if (result != null && result.size() == 1) {
            return KMyBatisUtil.fetchOne(result);
        }

        return null;
    }

    public APP_CONFIG fetchGlobalValue(String name) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("appId", null);
        filter.put("env", null);
        filter.put("name", name);

        List<APP_CONFIG> result = fetchByCriteria(filter);
        if (result != null && result.size() == 1) {
            return KMyBatisUtil.fetchOne(result);
        }

        return null;
    }



    @Override
	public APP_CONFIG fetchByAppIdAndEnvAndName(Long appId, KAppConfig.Env env, String name) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
        filter.put("env", env);
        filter.put("name", name);

        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
	}



	@Override
	public Map<String,Object> getConfig(Long appId, KAppConfig.Env env) {
		Map<String,Object> map = new HashMap<>();

        // get global vars
        List<APP_CONFIG> globalList = fetchByAppIdAndEnv(null,  null);

        for (APP_CONFIG config : globalList) {
            map.put(config.getName(), toValue(config.getValue()));
        }

        if (appId != null) {
		    // get app global vars
		    List<APP_CONFIG> configList = fetchByAppIdAndEnv(appId,  null);

		    for (APP_CONFIG config : configList) {
                map.put(config.getName(), toValue(config.getValue()));
		    }
        }

        if (env != null) {
            // get env global vars
            List<APP_CONFIG> configList = fetchByAppIdAndEnv(null,  env);

            for (APP_CONFIG config : configList) {
                map.put(config.getName(), toValue(config.getValue()));
            }
        }

		// next get environment specific params
		if (appId != null && env != null) {
            List<APP_CONFIG> configList  = fetchByAppIdAndEnv(appId, env);

			for (APP_CONFIG config : configList) {
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
    public  Map<String,Object> save(Long appId, KAppConfig.Env env, Map<String, Object> map) {
        for (String key : map.keySet()) {

            APP_CONFIG appConfig = fetchByAppIdAndEnvAndName(appId, env, key);

            if (appConfig == null) {
                appConfig = getNewObject();
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
    public  Map<String,Object> remove(Long appId, KAppConfig.Env env, List<String> keyList) {
        for (String key : keyList) {
            APP_CONFIG appConfig = fetchByAppIdAndEnvAndName(appId, env, key);
            remove(appConfig);
        }

        return getConfig(appId, env);
    }

}
