/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KApiLog;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractApiLogService<API_LOG extends KApiLog, API_LOG_EXAMPLE extends KEntityExample, MAPPER extends KMyBatisMapper<API_LOG, API_LOG_EXAMPLE>> extends KAbstractService<API_LOG,API_LOG_EXAMPLE,MAPPER>
		implements KApiLogService<API_LOG> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractApiLogService.class);
    


    protected abstract void updateCoords(Long apiLogId);



    @Override @Transactional
    public API_LOG add(API_LOG apiLog) {
        apiLog = super.add(apiLog);
        updateCoords(apiLog.getId());
        return apiLog;
    }
    


    @Override @Transactional
    public API_LOG update(API_LOG apiLog) {
        apiLog = super.update(apiLog);
        updateCoords(apiLog.getId());
        return apiLog;
    }


    
    @Override
    public API_LOG fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }
    

    
    @Override
    public List<API_LOG> fetchByOwnerId(Long ownerId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("ownerId", ownerId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }
    

    
    @Override
    public List<API_LOG> fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }
    

    
    @Override
    public List<API_LOG> fetchByAppId(Long appId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }
    

    
    @Override
    public List<API_LOG> fetchByClientId(String clientId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("clientId", clientId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }
    

    
	@Override
	public void validate(API_LOG apiLog) {
	   	 if (apiLog.getCreatedDate() == null) {
    		 apiLog.setCreatedDate(new Date());
    	 }
	   	 
         apiLog.setUpdatedDate(new Date());
         
         if (apiLog.getUid() == null) {
        	 apiLog.setUid(uuid());
         }
	}
}

