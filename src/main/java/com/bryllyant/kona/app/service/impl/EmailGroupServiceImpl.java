/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.EmailGroupMapper;
import com.bryllyant.kona.app.entity.EmailAddress;
import com.bryllyant.kona.app.entity.EmailGroup;
import com.bryllyant.kona.app.entity.EmailGroupAddress;
import com.bryllyant.kona.app.entity.EmailGroupExample;
import com.bryllyant.kona.app.service.EmailAddressService;
import com.bryllyant.kona.app.service.EmailGroupAddressService;
import com.bryllyant.kona.app.service.EmailGroupService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.EmailException;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service(EmailGroupService.SERVICE_PATH)
public class EmailGroupServiceImpl 
		extends KAbstractService<EmailGroup, EmailGroupExample, EmailGroupMapper>
		implements EmailGroupService {
	
	private static Logger logger = LoggerFactory.getLogger(EmailGroupServiceImpl.class);

	@Autowired
	private EmailGroupMapper emailGroupMapper;
    
	@Autowired
	private EmailAddressService emailAddressService;

	@Autowired
	private EmailGroupAddressService emailGroupAddressService;
    

	@Override @SuppressWarnings("unchecked")
	protected EmailGroupMapper getMapper() {
		return emailGroupMapper;
	}
    
    @Override
    public void validate(EmailGroup emailGroup) {
        if (emailGroup.getCreatedDate() == null) {
            emailGroup.setCreatedDate(new Date());
        }

        if (emailGroup.getUid() == null) {
            emailGroup.setUid(uuid());
        }

        String slug = KInflector.getInstance().slug(emailGroup.getName());

        emailGroup.setSlug(slug);
    }

    @Override
    public EmailGroup fetchBySlug(String slug) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
    public List<EmailGroupAddress> fetchGroupAddressList(String slug) {
        EmailGroup group = fetchBySlug(slug);
        return emailGroupAddressService.fetchByGroupId(group.getId());
    }


    @Override
    public EmailGroupAddress addGroupAddress(String slug, String email) {
        EmailGroup group = fetchBySlug(slug);
        return addGroupAddress(group.getId(), email);
    }


    private EmailGroupAddress addGroupAddress(Long groupId, String email) {
        EmailAddress address = emailAddressService.fetchByEmail(email);

        if (address == null) {
            address = new EmailAddress();
            address.setEmail(email);
            address.setEnabled(true);
            address.setCreatedDate(new Date());
            address = emailAddressService.add(address);
        }

        if (!emailAddressService.isValid(address)) {
            throw new EmailException("Invalid email address: " + email);
        }

        EmailGroupAddress ga = new EmailGroupAddress();
        ga.setAddressId(address.getId());
        ga.setGroupId(groupId);
        ga.setCreatedDate(new Date());
        return emailGroupAddressService.add(ga);
    }



    @Override
    public EmailGroupAddress removeGroupAddress(String slug, String email) {
        EmailAddress address = emailAddressService.fetchByEmail(email);
        EmailGroup group = fetchBySlug(slug);
        EmailGroupAddress ga = emailGroupAddressService.fetchByGroupIdAndAddressId(group.getId(), address.getId());
        emailGroupAddressService.remove(ga);
        return ga;
    }



    @Override
    public void addGroupAddressList(String slug, List<EmailAddress> emailAddressList) {
        EmailGroup group = fetchBySlug(slug);

        Date now = new Date();
        for (EmailAddress address : emailAddressList) {
            EmailGroupAddress ga = new EmailGroupAddress();
            ga.setAddressId(address.getId());
            ga.setGroupId(group.getId());
            ga.setCreatedDate(now);
            emailGroupAddressService.add(ga);
        }
    }



    @Override
    public EmailGroup create(String groupName, List<String> emailList) {
        EmailGroup group = create(groupName);
        for (String email : emailList) {
            try {
                addGroupAddress(group.getId(), email);
            } catch (EmailException e) {
                logger.warn("Cound not add email [{}] to group [{}]", email, groupName);
            }
        }
        return group;
    }



    @Override
    public EmailGroup create(String groupName) {
        EmailGroup group = new EmailGroup();
        group.setName(groupName);
        group.setCreatedDate(new Date());
        group = add(group);
        return group;
    }



    /**
     * @param groupName        Name of the group
     * @param maxCount         Max number of addresses in the group
     * @param sourceList       (optional) list of sources from which to pull email addresses
     * @param excludeGroupList (optional) don't include emails contained in the listed groups
     */
    @Override
    public EmailGroup create(String groupName, Long maxCount, List<String> sourceList, List<String> excludeGroupList) {
        EmailGroup group = create(groupName);

        if (maxCount != null) {
            List<EmailAddress> emailAddressList = emailAddressService.fetchRandom(maxCount, sourceList, excludeGroupList);

            if (emailAddressList.size() == 0) {
                logger.warn("EmailAddress fetchRandom yielded no results");
            }

            addGroupAddressList(groupName, emailAddressList);
        }

        return group;
    }
	
    
}
