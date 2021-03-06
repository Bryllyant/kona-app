/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.EmailCampaign;
import com.bryllyant.kona.app.entity.EmailContent;
import com.bryllyant.kona.app.entity.EmailGroup;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;


public interface EmailCampaignService extends KService, KEntityService<EmailCampaign> {
	String SERVICE_PATH = "rpc/EmailCampaignService";

    List<EmailCampaign> fetchByEmailGroupId(Long emailGroupId);

    List<EmailCampaign> fetchByCampaignChannelId(Long campaignChannelId);

    EmailCampaign create(
            User owner,
            String name,
            CampaignChannel channel,
            EmailGroup group,
            EmailContent content,
            String fromAddress,
            String replyTo,
            String subject,
            String permissionReminder,
            Boolean permissionReminderEnabled,
            String copyrighHolder,
            String companyName,
            String street1,
            String street2,
            String city,
            String state,
            String postalCode,
            String country
    );

    EmailCampaign start(EmailCampaign emailCampaign, Long throttleTime, boolean force);

    EmailCampaign updateStats(EmailCampaign campaign, boolean processNotifications);

    Email sendTestEmail(EmailCampaign emailCampaign, String toAddress);
}
