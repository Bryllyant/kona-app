/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.EmailAddress;
import com.bryllyant.kona.app.entity.EmailGroup;
import com.bryllyant.kona.app.entity.EmailGroupAddress;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;


public interface EmailGroupService extends KService, KEntityService<EmailGroup> {
	String SERVICE_PATH = "rpc/EmailGroupService";

    EmailGroup create(String groupName);

    EmailGroup create(String groupName, List<String> emailList);

    EmailGroup create(String groupName, Long maxCount, List<String> sourceList, List<String> excludeGroupList);

    EmailGroup fetchBySlug(String slug);

    EmailGroupAddress addGroupAddress(String slug, String email);

    void addGroupAddressList(String slug, List<EmailAddress> address);

    EmailGroupAddress removeGroupAddress(String slug, String email);

    List<EmailGroupAddress> fetchGroupAddressList(String slug);
	
}
