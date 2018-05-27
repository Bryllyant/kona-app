/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.EmailCampaign;
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
            EmailCampaign emailCampaign,
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
            EmailCampaign emailCampaign,
            String fromAddress,
            String replyTo,
            String subject,
            EmailContent content,
            EmailFooter footer,
            Long throttleTime
    ) throws EmailException;



    Email deliver(
            EmailCampaign emailCampaign,
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
            EmailCampaign emailCampaign,
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

    Email fetchByEmailCampaignIdAndToId(Long emailCampaignId, Long emailAddressId);

    List<Email> fetchByEmailCampaignId(Long emailCampaignId);

    EmailStats calcStats(List<Email> emailList);

    EmailStats calcStatsByEmailCampaignId(Long emailCampaignId);

    EmailEvent addEvent(EmailEvent event);
	
}
