/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KAuthRole;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

public abstract class KAbstractAuthRoleService<
		    AUTH_ROLE extends KAuthRole,
            AUTH_ROLE_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<AUTH_ROLE, AUTH_ROLE_EXAMPLE>
        >
		extends KAbstractService<AUTH_ROLE,AUTH_ROLE_EXAMPLE,MAPPER>
		implements KAuthRoleService<AUTH_ROLE> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractAuthRoleService.class);

	@Override
	public void validate(AUTH_ROLE role) {
		if (role.getCreatedDate() == null) {
			role.setCreatedDate(new Date());
		}

		role.setUpdatedDate(new Date());

		if (role.getUid() == null) {
		    role.setUid(uuid());
        }

        String slug = KInflector.getInstance().slug(role.getName());
        role.setSlug(slug);

	}

	@Override
	public AUTH_ROLE fetchByName(String name) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("name", name);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
}
