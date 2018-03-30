/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KAuthPriv;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

public abstract class KAbstractAuthPrivService<AUTH_PRIV extends KAuthPriv, AUTH_PRIV_EXAMPLE extends KEntityExample, MAPPER extends KMyBatisMapper<AUTH_PRIV, AUTH_PRIV_EXAMPLE>> extends KAbstractService<AUTH_PRIV,AUTH_PRIV_EXAMPLE,MAPPER>
		implements KAuthPrivService<AUTH_PRIV> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractAuthPrivService.class);

	@Override
	public void validate(AUTH_PRIV priv) {
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
	public AUTH_PRIV fetchByName(String name) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("name", name);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
}
