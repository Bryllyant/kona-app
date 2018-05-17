/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.EmailAddress;
import com.bryllyant.kona.app.entity.EmailGroup;
import com.bryllyant.kona.app.entity.EmailGroupAddress;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface EmailGroupService extends KService, KEntityService<EmailGroup> {
	String SERVICE_PATH = "rpc/EmailGroupService";

    EmailGroup create(String groupName);

    EmailGroup create(String groupName, List<String> emailList, boolean forceScrub);

    EmailGroup create(String groupName, Long maxCount, List<String> sourceList, List<String> excludeGroupList, boolean forceScrub);

    EmailGroup fetchBySlug(String slug);

    EmailGroupAddress addGroupAddress(EmailGroup group, String email, boolean forceScrub);

    void addGroupAddressList(EmailGroup group, List<EmailAddress> address, boolean forceScrub);

    @Transactional
    EmailGroupAddress addGroupAddress(EmailGroup group, EmailAddress address, boolean forceScrub);

    EmailGroupAddress removeGroupAddress(EmailGroup group, EmailAddress address);

    List<EmailGroupAddress> fetchGroupAddressList(EmailGroup group);
	
}
