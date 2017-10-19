/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.EntityNameRuleMapper;
import com.bryllyant.kona.app.entity.EntityNameRule;
import com.bryllyant.kona.app.entity.EntityNameRuleExample;
import com.bryllyant.kona.app.service.EntityNameRuleService;
import com.bryllyant.kona.app.service.KAbstractEntityNameRuleService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(EntityNameRuleService.SERVICE_PATH)
public class EntityNameRuleServiceImpl 
		extends KAbstractEntityNameRuleService<EntityNameRule,EntityNameRuleExample> 
		implements EntityNameRuleService {
	
	private static Logger logger = LoggerFactory.getLogger(EntityNameRuleServiceImpl.class);

	@Autowired
	private EntityNameRuleMapper entityNameRuleDao;
	
	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected EntityNameRuleMapper getDao() {
		return entityNameRuleDao;
	}

	// ----------------------------------------------------------------------------
	
	@Override
	protected EntityNameRuleExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
			Map<String, Object> filter, boolean distinct) {
		EntityNameRuleExample example = new EntityNameRuleExample();

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

}
