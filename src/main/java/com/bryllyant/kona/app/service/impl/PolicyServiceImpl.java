/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PolicyMapper;
import com.bryllyant.kona.app.entity.Policy;
import com.bryllyant.kona.app.entity.PolicyExample;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.PolicyService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(PolicyService.SERVICE_PATH)
public class PolicyServiceImpl
		extends KAbstractService<Policy, PolicyExample, PolicyMapper>
		implements PolicyService {
	
	private static Logger logger = LoggerFactory.getLogger(PolicyServiceImpl.class);

	@Autowired
	private PolicyMapper policyMapper;
    
	@Override @SuppressWarnings("unchecked")
	protected PolicyMapper getMapper() {
		return policyMapper;
	}

    @Override
    protected boolean entityHasBlobs() {
        return true;
    }

    @Override
    public void validate(Policy policy) {
        if (policy.getCreatedDate() == null) {
            policy.setCreatedDate(new Date());
        }

        if (policy.getUid() == null) {
            policy.setUid(uuid());
        }

        policy.setUpdatedDate(new Date());

        if (policy.isActive()) {
            unsetActive(policy);

            if (policy.getPublishedDate() == null) {
                policy.setPublishedDate(new Date());
            }
        }
    }


    @Override
    public Policy fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
    public Policy fetchByTypeAndVersion(Policy.Type type, Integer version) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("type", type);
        filter.put("version", version);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
    public List<Policy> fetchByType(Policy.Type type) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("type", type);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    @Override
    public Policy fetchActive(Policy.Type type) {
        Policy active = null;

        List<Policy> list = fetchByType(type);

        for (Policy item : list) {
            if (item.isActive()) {
                active = item;
                break;
            }
        }

        return active;
    }


    private void unsetActive(Policy current) {
        Policy item = fetchActive(current.getType());

        if (item != null) {
            if (current.getId() == null || !current.getId().equals(item.getId())) {
                item.setActive(false);
                getMapper().updateByPrimaryKey(item);
            }
        }
    }
}
