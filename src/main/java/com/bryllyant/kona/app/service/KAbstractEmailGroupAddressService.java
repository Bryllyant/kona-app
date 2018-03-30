package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KEmailGroupAddress;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractEmailGroupAddressService<
        EMAIL_GROUP_ADDRESS extends KEmailGroupAddress,
        EMAIL_GROUP_ADDRESS_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<EMAIL_GROUP_ADDRESS, EMAIL_GROUP_ADDRESS_EXAMPLE>>
        extends KAbstractService<EMAIL_GROUP_ADDRESS,EMAIL_GROUP_ADDRESS_EXAMPLE,MAPPER>
		implements KEmailGroupAddressService<EMAIL_GROUP_ADDRESS> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractEmailGroupAddressService.class);
	

	
	@Override
	public void validate(EMAIL_GROUP_ADDRESS emailGroupAddress) {
		if (emailGroupAddress.getCreatedDate() == null) {
			emailGroupAddress.setCreatedDate(new Date());
		}

		if (emailGroupAddress.getUid() == null) {
		    emailGroupAddress.setUid(uuid());
        }

		emailGroupAddress.setUpdatedDate(new Date());
	}



	@Override
	public EMAIL_GROUP_ADDRESS fetchByGroupIdAndAddressId(Long groupId, Long addressId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("groupId", groupId);
		filter.put("addressId", addressId);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}



	@Override
	public List<EMAIL_GROUP_ADDRESS> fetchByGroupId(Long groupId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("groupId", groupId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}



	@Override
	public List<EMAIL_GROUP_ADDRESS> fetchByAddressId(Long addressId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("addressId", addressId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
}

