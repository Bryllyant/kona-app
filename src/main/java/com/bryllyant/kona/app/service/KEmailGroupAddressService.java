package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KEmailGroupAddress;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface KEmailGroupAddressService<EMAIL_GROUP_ADDRESS extends KEmailGroupAddress> 
		extends KService, KEntityService<EMAIL_GROUP_ADDRESS> {
			
	EMAIL_GROUP_ADDRESS fetchByGroupIdAndAddressId(Long groupId, Long addressId);
	
	List<EMAIL_GROUP_ADDRESS> fetchByGroupId(Long groupId);
	
	List<EMAIL_GROUP_ADDRESS> fetchByAddressId(Long addressId);
}
