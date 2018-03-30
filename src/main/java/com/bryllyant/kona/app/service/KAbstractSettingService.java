/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KSetting;
import com.bryllyant.kona.app.entity.KUser;
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

public abstract class KAbstractSettingService<SETTING extends KSetting, SETTING_EXAMPLE extends KEntityExample, MAPPER extends KMyBatisMapper<SETTING, SETTING_EXAMPLE>,USER extends KUser>
		extends KAbstractService<SETTING,SETTING_EXAMPLE,MAPPER>
		implements KSettingService<SETTING,USER> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractSettingService.class);
    

    protected abstract SETTING getNewObject();
    

	@Override
	public void validate(SETTING setting) {
	   	 if (setting.getCreatedDate() == null) {
    		 setting.setCreatedDate(new Date());
    	 }

         setting.setUpdatedDate(new Date());

	   	 if (setting.getUid() == null) {
	   	 	setting.setUid(uuid());
		 }
	}
	

    @Override
    public SETTING fetchByUserIdAndName(Long userId, String name) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        filter.put("name", name);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }
    

    @Override
    public SETTING fetchByAccountIdAndName(Long accountId, String name) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
        filter.put("name", name);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
	public SETTING fetchGlobalByName(String name) {
		return fetchByUserIdAndName(null, name);
	}
    

    /**
     * NOTE: this method assumes the String value that is set is already in JSON format.
     */
    @Override @Transactional
    public SETTING save(SETTING setting) {
        if (setting.getId() != null) {
            return update(setting);
        }

        // see if we have an existing record
        SETTING s = fetchByUserIdAndName(setting.getUserId(), setting.getName());
        
        if (s == null) {
            // if this is a local value, check if the value is different from global
            if (setting.getUserId() != null) {
                SETTING s2 = fetchGlobalByName(setting.getName());
                
            	logger.debug("fetched global setting for name {}: {}", setting.getName(), s2);
                
            	if (s2 != null && s2.getValue().equals(setting.getValue())) {
            		
            		// if same as global and overwrite is set then add record
            		if (setting.isOverrideGlobal()) {
            			logger.debug("global setting equal to new value: {}; overwriteGlobal not set; returning setting", setting.getValue());
            			return add(setting);
            		} else {
            			// otherwise keep global value and return current setting
            			return setting;
            		}
            	} else {
            		return add(setting);
            	}
            } else {
            	// this is a new Global setting so add it.
            	logger.debug("setting not found for userId {} and name {} .. adding new record", setting.getUserId(), setting.getName());
                
            	return add(setting);
            }
        }
        
        logger.debug("found existing setting userId {} and name {}: {}", setting.getUserId(), setting.getName(), s);
        
        // if no change in value, return existing saved value
        if (s.getValue().equals(setting.getValue())) {
        	return s;
        }
        

        logger.debug("updating: saved setting {} with new value", s, setting.getValue());
        s.setValue(setting.getValue());
        return update(s);
    }


    @Override @Transactional
    public void save(Long userId, Long accountId, Map<String,Object> config, boolean overwriteGlobal) {
    	for (String key : config.keySet()) {
    		SETTING setting = null;

    		if (userId != null) {
    		    setting = fetchByUserIdAndName(userId, key);
    		} else {
    		    setting = fetchByAccountIdAndName(accountId, key);
    		}

    		if (setting == null) {
    			setting = getNewObject();
    			setting.setUserId(userId);
    			setting.setAccountId(accountId);
    			setting.setName(key);
    			setting.setOverrideGlobal(overwriteGlobal);
    			setting.setCreatedDate(new Date());
    		}

    		setting.setValue(KJsonUtil.toJson(config.get(key)));

    		save(setting);
    	}
    }
    

	@Override
	public List<SETTING> fetchGlobal() {
		return fetchByUserId(null);
	}
	

	@Override
	public List<SETTING> fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(0, 99999, null, filter, false);
	}
	

    @Override
    public List<SETTING> fetchByAccountId(Long accountId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }
    

	@Override
	public Map<String,Object> getGlobalSettings() {
		return getUserSettings(null);
	}
	

	@Override
	public Map<String,Object> getUserSettings(USER user) {
		Map<String,Object> map = new HashMap<>();

		// first get all global vars
		List<SETTING> settingList = fetchGlobal();

		for (SETTING setting : settingList) {
		    Object value = KJsonUtil.fromJson(setting.getValue());
			map.put(setting.getName(), value);
		}
		
		  // account settings
        if (user != null && user.getAccountId() != null) {
            settingList = fetchByAccountId(user.getAccountId());

            for (SETTING setting : settingList) {
                Object value = KJsonUtil.fromJson(setting.getValue());
                map.put(setting.getName(), value);
            }
        }

		// user settings
		if (user != null) {
			settingList = fetchByUserId(user.getId());
			for (SETTING setting : settingList) {
			    Object value = KJsonUtil.fromJson(setting.getValue());
				map.put(setting.getName(), value);
			}
		}

		// MapConfiguration is not serializable
		//return new MapConfiguration(map);
		return map;
	}


    @Override
    public Map<String,Object> getAccountSettings(Long accountId) {
        Map<String,Object> map = new HashMap<>();

        // first get all global vars
        List<SETTING> settingList = fetchGlobal();

        for (SETTING setting : settingList) {
            Object value = KJsonUtil.fromJson(setting.getValue());
            map.put(setting.getName(), value);
        }

        // next get environment specific params
        if (accountId != null) {
            settingList = fetchByAccountId(accountId);

            for (SETTING setting : settingList) {
                Object value = KJsonUtil.fromJson(setting.getValue());
                map.put(setting.getName(), value);
            }
        }

        // MapConfiguration is not serializable
        //return new MapConfiguration(map);
        return map;
    }
}
