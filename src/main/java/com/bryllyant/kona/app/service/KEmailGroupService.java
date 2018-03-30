package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KEmailAddress;
import com.bryllyant.kona.app.entity.KEmailGroup;
import com.bryllyant.kona.app.entity.KEmailGroupAddress;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface KEmailGroupService<EMAIL_GROUP extends KEmailGroup,
								    EMAIL_ADDRESS extends KEmailAddress,
								    EMAIL_GROUP_ADDRESS extends KEmailGroupAddress> 
		extends KService, KEntityService<EMAIL_GROUP> {
	
    EMAIL_GROUP create(String groupName);
    
    EMAIL_GROUP create(String groupName, List<String> emailList);
    
    EMAIL_GROUP create(String groupName, Long maxCount, List<String> sourceList, List<String> excludeGroupList);
    
	EMAIL_GROUP fetchBySlug(String slug);
	
	EMAIL_GROUP_ADDRESS addGroupAddress(String slug, String email);
	
	void addGroupAddressList(String slug, List<EMAIL_ADDRESS> address);
	
	EMAIL_GROUP_ADDRESS removeGroupAddress(String slug, String email);
	
    List<EMAIL_GROUP_ADDRESS> fetchGroupAddressList(String slug);
}
