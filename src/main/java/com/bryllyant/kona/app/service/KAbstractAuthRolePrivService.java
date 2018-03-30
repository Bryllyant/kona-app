/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KAuthPriv;
import com.bryllyant.kona.app.entity.KAuthRole;
import com.bryllyant.kona.app.entity.KAuthRolePriv;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractAuthRolePrivService<
		    AUTH_ROLE_PRIV extends KAuthRolePriv,
            AUTH_ROLE_PRIV_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<AUTH_ROLE_PRIV, AUTH_ROLE_PRIV_EXAMPLE>,
		    AUTH_ROLE extends KAuthRole,
			AUTH_PRIV extends KAuthPriv
        >
		extends KAbstractService<AUTH_ROLE_PRIV,AUTH_ROLE_PRIV_EXAMPLE,MAPPER>
		implements KAuthRolePrivService<AUTH_ROLE_PRIV, AUTH_ROLE, AUTH_PRIV> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractAuthRolePrivService.class);

    protected abstract AUTH_ROLE_PRIV getNewObject();
    protected abstract <S extends KAuthPrivService<AUTH_PRIV>> S getAuthPrivService();
    protected abstract <S extends KAuthRoleService<AUTH_ROLE>> S getAuthRoleService();

	@Override
	public void validate(AUTH_ROLE_PRIV rolePriv) {
		if (rolePriv.getCreatedDate() == null) {
            rolePriv.setCreatedDate(new Date());
		}

        rolePriv.setUpdatedDate(new Date());

		if (rolePriv.getUid() == null) {
		    rolePriv.setUid(uuid());
        }
	}


	@Override
	public AUTH_ROLE_PRIV fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

    @Override
    public List<AUTH_ROLE_PRIV> fetchByRoleId(Long roleId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("roleId", roleId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    @Override
    public List<AUTH_ROLE_PRIV> fetchByPrivId(Long privId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("privId", privId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    public AUTH_ROLE_PRIV fetchByRoleIdAndPrivId(Long roleId, Long privId) {
        Map<String,Object> filter = KMyBatisUtil.filter()
                .and("roleId", roleId)
                .and("privId", privId)
                .build();

        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

    public List<AUTH_PRIV> fetchPrivsByRole(AUTH_ROLE role) {
        List<AUTH_ROLE_PRIV> rolePrivs = fetchByRoleId(role.getId());

        List<AUTH_PRIV> result = new ArrayList<>();

        for (AUTH_ROLE_PRIV rolePriv : rolePrivs) {
            AUTH_PRIV priv = getAuthPrivService().fetchById(rolePriv.getPrivId());
            result.add(priv);
        }

        return result;
    }


    public AUTH_ROLE_PRIV create(AUTH_ROLE role, AUTH_PRIV priv) {
        AUTH_ROLE_PRIV rolePriv = fetchByRoleIdAndPrivId(role.getId(), priv.getId());

        if (rolePriv == null) {
            rolePriv = getNewObject();
            rolePriv.setRoleId(role.getId());
            rolePriv.setPrivId(priv.getId());
            rolePriv = add(rolePriv);
        }

        return rolePriv;
    }


    public void remove(AUTH_ROLE role, AUTH_PRIV priv) {
        AUTH_ROLE_PRIV rolePriv = fetchByRoleIdAndPrivId(role.getId(), priv.getId());
        if (rolePriv != null) {
            remove(rolePriv);
        }
    }
}
