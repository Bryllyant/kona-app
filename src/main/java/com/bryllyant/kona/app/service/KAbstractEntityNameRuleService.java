/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KEntityNameRule;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class KAbstractEntityNameRuleService<T extends KEntityNameRule, EXAMPLE extends KEntityExample, MAPPER extends KMyBatisMapper<T, EXAMPLE>> extends KAbstractService<T, EXAMPLE,MAPPER>
        implements KEntityNameRuleService<T> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractEntityNameRuleService.class);

    private List<T> rules = null;



    public void validate(T rule) {
        if (rule.getCreatedDate() == null) {
            rule.setCreatedDate(new Date());
        }

        rule.setUpdatedDate(new Date());
    }



    @Override
    public T fetchByUid(String uid) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 9999, null, filter, false));
    }



    @Override
    public T fetchByPattern(String pattern) {
        T rule = null;
        Map<String, Object> filter = KMyBatisUtil.createFilter("pattern", pattern);
        List<T> list = fetchByCriteria(0, 9999, null, filter, false);
        if (list != null && list.size() == 1) {
            rule = list.get(0);
        }
        return rule;
    }



    @Override
    public T fetchForName(String name) {
        if (rules == null) {
            rules = fetchAll();
        }

        logger.debug("EntityNameRuleService: checking rule match for name: " + name);
        for (T rule : rules) {
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
    public List<T> fetchAll() {
        return fetchByCriteria(0, 999999, null, null, false);
    }



    @Override
    public boolean isReserved(String name) {
        T rule = fetchForName(name);

        if (rule != null) {
            return rule.isReserved();
        }

        return false;
    }



    @Override
    public boolean isBlackListed(String name) {
        T rule = fetchForName(name);

        if (rule != null) {
            return rule.isBlackListed();
        }

        return false;
    }



    @Override
    public boolean isAcceptable(String name) {
        T rule = fetchForName(name);

        if (rule != null) {
            return !rule.isBlackListed() && !rule.isReserved();
        }

        return true;
    }

}
