/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.EmailContent;
import com.bryllyant.kona.app.entity.EmailEvent;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.model.EmailFooter;
import com.bryllyant.kona.app.model.EmailStats;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;


public interface EmailService extends KService, KEntityService<Email> {
	String SERVICE_PATH = "rpc/EmailService";

    String getEmailTestDomain();

    Email send(
            String body,
            String subject,
            String from,
            String replyTo,
            String to,
            boolean html,
            List<File> attachmentList,
            EmailFooter footer
    ) throws EmailException;



    void sendRawSMTP(
            String body,
            String subject,
            String from,
            String replyTo,
            String to,
            String cc,
            String bcc,
            boolean html,
            List<File> attachmentList
    ) throws EmailException;



    String sendRawAWS(
            String body,
            String subject,
            String from,
            String replyTo,
            String to,
            String cc,
            String bcc,
            boolean html,
            List<File> mediaList
    ) throws EmailException;



    String sendRawAWS(
            String from,
            String replyTo,
            String to,
            String subject,
            String textBody,
            String htmlBody,
            List<File> attachmentList
    ) throws EmailException;



    // replyTo: when recipient replies, each reply-to address will receive the reply
    // returnPath: the address to which bounces and complaints are forwarded to
    String sendRawAWS(
            String from,
            String[] replyTo,
            String returnPath,
            String[] to,
            String[] cc,
            String[] bcc,
            String subject,
            String textBody,
            String htmlBody,
            List<File> attachmentList
    ) throws EmailException;



    void deliver(
            Long campaignChannelId,
            Long emailGroupId,
            String fromAddress,
            String replyTo,
            String subject,
            String text,
            String html,
            List<File> attachmentList,
            EmailFooter footer,
            Long throttleTime
    ) throws EmailException;



    void deliver(
            Long campaignChannelId,
            Long emailGroupId,
            String from,
            String replyTo,
            String subject,
            EmailContent content,
            EmailFooter footer,
            Long throttleTime
    ) throws EmailException;



    Email deliver(
            String from,
            String replyTo,
            String to,
            String subject,
            String text,
            String html,
            List<File> attachmentList,
            EmailFooter footer
    ) throws EmailException;




    Email deliver(
            String from,
            String replyTo,
            String to,
            String subject,
            EmailContent content,
            EmailFooter footer
    ) throws EmailException;



    void processLocalQueue();

    void processSESNotifications();

    Email fetchByUid(String uid);

    Email fetchBySesId(String sesId);

    Email fetchByCampaignChannelIdAndToId(Long campaignChannelId, Long emailAddressId);

    List<Email> fetchByEmailGroupId(Long emailGroupId);

    List<Email> fetchByEmailGroupSlug(String groupSlug);

    List<Email> fetchByCampaignId(Long campaignId);

    List<Email> fetchByCampaignGroupId(Long campaignGroupId);

    List<Email> fetchByCampaignChannelId(Long campaignChannelId);

    EmailStats calcStats(List<Email> emailList);

    EmailStats calcStatsByGroupSlug(String groupSlug);

    EmailStats calcStatsByCampaignId(Long campaignId);

    EmailStats calcStatsByCampaignGroupId(Long campaignGroupId);

    EmailStats calcStatsByCampaignChannelId(Long campaignChannelId);

    EmailEvent addEvent(EmailEvent event);
	
}
