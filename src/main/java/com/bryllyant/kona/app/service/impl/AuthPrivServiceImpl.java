/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.AuthPrivMapper;
import com.bryllyant.kona.app.entity.AuthPriv;
import com.bryllyant.kona.app.entity.AuthPrivExample;
import com.bryllyant.kona.app.service.AuthPrivService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service(AuthPrivService.SERVICE_PATH)
public class AuthPrivServiceImpl
		extends KAbstractService<AuthPriv, AuthPrivExample, AuthPrivMapper>
		implements AuthPrivService {

	private static Logger logger = LoggerFactory.getLogger(AuthPrivServiceImpl.class);

	@Autowired
	private AuthPrivMapper authPrivMapper;


	@Override
	@SuppressWarnings("unchecked")
	protected AuthPrivMapper getMapper() {
		return authPrivMapper;
	}

    @Override
    public void validate(AuthPriv priv) {
        if (priv.getCreatedDate() == null) {
            priv.setCreatedDate(new Date());
        }

        priv.setUpdatedDate(new Date());

        if (priv.getUid() == null) {
            priv.setUid(uuid());
        }

        String slug = KInflector.getInstance().slug(priv.getName());
        priv.setSlug(slug);

    }

    @Override
    public AuthPriv fetchByName(String name) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("name", name);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }


}
