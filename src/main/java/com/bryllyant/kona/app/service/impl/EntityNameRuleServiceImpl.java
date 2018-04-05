/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.EntityNameRuleMapper;
import com.bryllyant.kona.app.entity.EntityNameRule;
import com.bryllyant.kona.app.entity.EntityNameRuleExample;
import com.bryllyant.kona.app.service.EntityNameRuleService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service(EntityNameRuleService.SERVICE_PATH)
public class EntityNameRuleServiceImpl 
		extends KAbstractService<EntityNameRule, EntityNameRuleExample, EntityNameRuleMapper>
        implements EntityNameRuleService {
	
	private static Logger logger = LoggerFactory.getLogger(EntityNameRuleServiceImpl.class);

    private List<EntityNameRule> rules = null;

	@Autowired
	private EntityNameRuleMapper entityNameRuleMapper;


	@Override @SuppressWarnings("unchecked")
	protected EntityNameRuleMapper getMapper() {
		return entityNameRuleMapper;
	}


    public void validate(EntityNameRule rule) {
        if (rule.getCreatedDate() == null) {
            rule.setCreatedDate(new Date());
        }

        rule.setUpdatedDate(new Date());

        if (rule.getUid() == null) {
            rule.setUid(uuid());
        }
    }


    @Override
    public EntityNameRule fetchByPattern(String pattern) {
        EntityNameRule rule = null;

        Map<String, Object> filter = KMyBatisUtil.createFilter("pattern", pattern);

        List<EntityNameRule> list = fetchByCriteria(0, 9999, null, filter, false);

        if (list != null && list.size() == 1) {
            rule = list.get(0);
        }

        return rule;
    }



    @Override
    public EntityNameRule fetchForName(String name) {
        if (rules == null) {
            rules = fetchAll();
        }

        logger.debug("EntityNameRuleService: checking rule match for name: " + name);

        for (EntityNameRule rule : rules) {
            String pattern = rule.getPattern();
            //logger.debug("UsernameRuleService: checking rule pattern: " + pattern);

            Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
            Matcher m = p.matcher(name);

            if (m.matches()) {
                logger.debug("EntityNameRuleService: matched pattern: " + pattern);
                return rule;
            }
        }

        logger.debug("EntityNameRuleService: no pattern matched.");
        return null;
    }


    @Override
    public List<EntityNameRule> fetchAll() {
        return fetchByCriteria(0, 999999, null, null, false);
    }

    @Override
    public boolean isReserved(String name) {
        EntityNameRule rule = fetchForName(name);

        if (rule != null) {
            return rule.isReserved();
        }

        return false;
    }

    @Override
    public boolean isBlackListed(String name) {
        EntityNameRule rule = fetchForName(name);

        if (rule != null) {
            return rule.isBlackListed();
        }

        return false;
    }

    @Override
    public boolean isAcceptable(String name) {
        EntityNameRule rule = fetchForName(name);

        if (rule != null) {
            return !rule.isBlackListed() && !rule.isReserved();
        }

        return true;
    }

}
