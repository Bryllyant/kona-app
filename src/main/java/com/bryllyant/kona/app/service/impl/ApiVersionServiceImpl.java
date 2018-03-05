/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.ApiVersionMapper;
import com.bryllyant.kona.app.entity.ApiVersion;
import com.bryllyant.kona.app.entity.ApiVersionExample;
import com.bryllyant.kona.app.service.ApiVersionService;
import com.bryllyant.kona.app.service.KAbstractApiVersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(ApiVersionService.SERVICE_PATH)
public class ApiVersionServiceImpl 
		extends KAbstractApiVersionService<ApiVersion,ApiVersionExample> 
		implements ApiVersionService {
	
	private static Logger logger = LoggerFactory.getLogger(ApiVersionServiceImpl.class);

	@Autowired
	private ApiVersionMapper apiVersionDao;
	


	@Override @SuppressWarnings("unchecked")
	protected ApiVersionMapper getDao() {
		return apiVersionDao;
	}


	
//	@Override
//	protected ApiVersionExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
//			Map<String, Object> filter, boolean distinct) {
//		ApiVersionExample example = new ApiVersionExample();
//
//        if (sortOrder != null) {
//            example.setOrderByClause(KMyBatisUtil.getOrderByString(sortOrder));
//        } else {
//            example.setOrderByClause("published_date");
//        }
//
//		if (startRow == null) startRow = 0;
//		if (resultSize == null) resultSize = 99999999;
//
//        example.setOffset(startRow);
//        example.setLimit(resultSize);
//		example.setDistinct(distinct);
//
//		KMyBatisUtil.buildExample(example.or().getClass(), example.or(), filter);
//
//		return example;
//	}


	@Override
	protected ApiVersionExample getEntityExampleObject() {
		return new ApiVersionExample();
	}
}
