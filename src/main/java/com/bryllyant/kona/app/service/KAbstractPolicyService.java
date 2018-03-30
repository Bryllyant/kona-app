package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KPolicy;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractPolicyService<
        POLICY extends KPolicy,
        POLICY_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<POLICY, POLICY_EXAMPLE>>
        extends KAbstractService<POLICY,POLICY_EXAMPLE,MAPPER>
        implements KPolicyService<POLICY> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractPolicyService.class);

	@Override
    protected boolean entityHasBlobs() {
	    return true;
    }

	@Override
	public void validate(POLICY policy) {
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
	public POLICY fetchByUid(String uid) {
	    Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
	    return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}


	@Override
	public POLICY fetchByTypeAndVersion(KPolicy.Type type, Integer version) {
	    Map<String,Object> filter = KMyBatisUtil.createFilter("type", type);
	    filter.put("version", version);
	    return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}


	@Override
	public List<POLICY> fetchByType(KPolicy.Type type) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("type", type);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
	
	@Override
	public POLICY fetchActive(KPolicy.Type type) {
	    POLICY active = null;

	    List<POLICY> list = fetchByType(type);

	    for (POLICY item : list) {
	        if (item.isActive()) {
	            active = item;
	            break;
	        }
	    }

	    return active;
	}


	private void unsetActive(POLICY current) {
	    POLICY item = fetchActive(current.getType());

        if (item != null) {
            if (current.getId() == null || !current.getId().equals(item.getId())) {
                item.setActive(false);
                getMapper().updateByPrimaryKey(item);
            }
        }
    }	
}
