/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PolicyMapper;
import com.bryllyant.kona.app.entity.Policy;
import com.bryllyant.kona.app.entity.PolicyExample;
import com.bryllyant.kona.app.service.KAbstractPolicyService;
import com.bryllyant.kona.app.service.PolicyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(PolicyService.SERVICE_PATH)
public class PolicyServiceImpl
		extends KAbstractPolicyService<Policy, PolicyExample, PolicyMapper>
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
    protected PolicyExample getEntityExampleObject() { return new PolicyExample(); }




}
