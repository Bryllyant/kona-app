/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.ApiLogMapper;
import com.bryllyant.kona.app.entity.ApiLog;
import com.bryllyant.kona.app.entity.ApiLogExample;
import com.bryllyant.kona.app.service.ApiLogService;
import com.bryllyant.kona.app.service.KAbstractApiLogService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(ApiLogService.SERVICE_PATH)
public class ApiLogServiceImpl 
		extends KAbstractApiLogService<ApiLog,ApiLogExample> 
		implements ApiLogService {
	
	private static Logger logger = LoggerFactory.getLogger(ApiLogServiceImpl.class);

	// ----------------------------------------------------------------------------

	@Autowired
	private ApiLogMapper apiVersionDao;
	
	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected ApiLogMapper getDao() {
		return apiVersionDao;
	}

	// ----------------------------------------------------------------------------
	
	@Override
	protected ApiLogExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
			Map<String, Object> filter, boolean distinct) {
		ApiLogExample example = new ApiLogExample();

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
    protected void updateCoords(Long apiLogId) {
        getDao().updateCoords(apiLogId);
    }

	// ----------------------------------------------------------------------------

    @Override 
    public List<ApiLog> fetchProximate(Double latitude, Double longitude, Double radius, Date startDate, Date endDate) {
        return getDao().selectProximate(latitude, longitude, radius, startDate, endDate);
    }

}
