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
import com.bryllyant.kona.app.service.EmailException;
import com.bryllyant.kona.app.service.EmailGroupAddressService;
import com.bryllyant.kona.app.service.EmailGroupService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.util.KInflector;
import com.bryllyant.kona.util.KJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        emailGroup.setUpdatedDate(new Date());

        if (emailGroup.getUid() == null) {
            emailGroup.setUid(uuid());
        }

        String slug = KInflector.getInstance().slug(emailGroup.getName());

        emailGroup.setSlug(slug);
    }

    @Override
    public EmailGroup fetchBySlug(String slug) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }


    @Override
    public List<EmailGroupAddress> fetchGroupAddressList(EmailGroup group) {
        return emailGroupAddressService.fetchByGroupId(group.getId());
    }


    @Override @Transactional
    public EmailGroupAddress addGroupAddress(EmailGroup group, String email, boolean forceScrub) {
        EmailAddress address = emailAddressService.fetchByEmail(email);

        if (address == null) {
            address = new EmailAddress();
            address.setEmail(email);
            address.setEnabled(true);
            address.setCreatedDate(new Date());
            address = emailAddressService.add(address);
        }

        return addGroupAddress(group, address, forceScrub);
    }

    @Override @Transactional
    public EmailGroupAddress addGroupAddress(EmailGroup group, EmailAddress address, boolean forceScrub) {
        if (!emailAddressService.isValid(address, forceScrub)) {
            throw new EmailException("Invalid email address: " + address);
        }

        EmailGroupAddress ga = new EmailGroupAddress();
        ga.setAddressId(address.getId());
        ga.setGroupId(group.getId());

        return emailGroupAddressService.add(ga);
    }

    @Override @Transactional
    public EmailGroupAddress removeGroupAddress(EmailGroup group, EmailAddress address) {
        EmailGroupAddress ga = emailGroupAddressService.fetchByGroupIdAndAddressId(group.getId(), address.getId());
        emailGroupAddressService.remove(ga);
        return ga;
    }

    @Override @Transactional
    public void addGroupAddressList(EmailGroup group, List<EmailAddress> emailAddressList, boolean forceScrub) {
        Date now = new Date();

        for (EmailAddress address : emailAddressList) {
            boolean valid = emailAddressService.isValid(address, forceScrub);

            if (valid) {
                EmailGroupAddress ga = new EmailGroupAddress();
                ga.setAddressId(address.getId());
                ga.setGroupId(group.getId());
                ga.setCreatedDate(now);
                emailGroupAddressService.add(ga);
            } else {
                logger.debug("[addGroupAddressList]  Email address is not valid:\n" + address);
            }
        }
    }

    @Override @Transactional
    public EmailGroup create(String groupName, List<String> emailList, boolean forceScrub) {
        EmailGroup group = create(groupName);

        for (String email : emailList) {
            try {
                addGroupAddress(group, email, forceScrub);
            } catch (EmailException e) {
                logger.warn("Could not add email [{}] to group [{}]", email, groupName);
            }
        }

        return group;
    }

    @Override @Transactional
    public EmailGroup create(String groupName) {
        EmailGroup group = new EmailGroup();
        group.setName(groupName);
        group.setCreatedDate(new Date());
        group = add(group);
        return group;
    }


    @Override @Transactional
    public EmailGroup create(
            String groupName,
            Long maxCount,
            List<String> includeSourceList,
            List<String> excludeSourceList,
            List<String> includeGroupList,
            List<String> excludeGroupList,
            boolean forceScrub
    ) {

        logger.debug("[create]\ngroupName: {}"
                + "\nmaxCount: {}"
                + "\nincludeSourceList: {}"
                + "\nexcludeSourceList: {}"
                + "\nincludeGroupList: {}"
                + "\nexcludeGroupList: {}"
                + "\nforceScrub: {}",
                groupName,
                maxCount,
                KJsonUtil.toJson(includeSourceList),
                KJsonUtil.toJson(excludeSourceList),
                KJsonUtil.toJson(includeGroupList),
                KJsonUtil.toJson(excludeGroupList),
                forceScrub
        );

        EmailGroup group = create(groupName);

        if (maxCount != null) {
            new Thread(() -> {

                List<EmailAddress> emailAddressList = emailAddressService.fetchRandom(
                        maxCount,
                        includeSourceList,
                        excludeSourceList,
                        includeGroupList,
                        excludeGroupList
                );

                if (emailAddressList.size() == 0) {
                    logger.warn("EmailAddress fetchRandom yielded no results");
                }

                addGroupAddressList(group, emailAddressList, forceScrub);
            }).start();
        }

        return group;
    }
}
