/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.ApiLogMapper;
import com.bryllyant.kona.app.entity.ApiLog;
import com.bryllyant.kona.app.entity.ApiLogExample;
import com.bryllyant.kona.app.service.ApiLogService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(ApiLogService.SERVICE_PATH)
public class ApiLogServiceImpl 
		extends KAbstractService<ApiLog, ApiLogExample, ApiLogMapper>
		implements ApiLogService {
	
	private static Logger logger = LoggerFactory.getLogger(ApiLogServiceImpl.class);


	@Autowired
	private ApiLogMapper apiVersionMapper;
	

	@Override @SuppressWarnings("unchecked")
	protected ApiLogMapper getMapper() {
		return apiVersionMapper;
	}

    protected void updateCoords(Long apiLogId) {
        getMapper().updateCoords(apiLogId);
    }

    @Override
    public void validate(ApiLog apiLog) {
        if (apiLog.getCreatedDate() == null) {
            apiLog.setCreatedDate(new Date());
        }

        apiLog.setUpdatedDate(new Date());

        if (apiLog.getUid() == null) {
            apiLog.setUid(uuid());
        }
    }


    @Override @Transactional
    public ApiLog add(ApiLog apiLog) {
        apiLog = super.add(apiLog);
        updateCoords(apiLog.getId());
        return apiLog;
    }



    @Override @Transactional
    public ApiLog update(ApiLog apiLog) {
        apiLog = super.update(apiLog);
        updateCoords(apiLog.getId());
        return apiLog;
    }


    @Override
    public List<ApiLog> fetchByOwnerId(Long ownerId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("ownerId", ownerId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }



    @Override
    public List<ApiLog> fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }



    @Override
    public List<ApiLog> fetchByAppId(Long appId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }



    @Override
    public List<ApiLog> fetchByClientId(String clientId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("clientId", clientId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }


    @Override
    public List<ApiLog> fetchProximate(
    		Double latitude,
            Double longitude,
            Double radius,
            Date startDate,
            Date endDate,
            List<Long> objectIdList
    ) {
        return getMapper().selectProximate(latitude, longitude, radius, startDate, endDate, objectIdList);
    }

}
