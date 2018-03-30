package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KEmail;
import com.bryllyant.kona.app.entity.KEmailAttachment;
import com.bryllyant.kona.app.entity.KEmailContent;
import com.bryllyant.kona.app.entity.KEmailEvent;
import com.bryllyant.kona.app.model.KEmailFooter;
import com.bryllyant.kona.app.model.KEmailStats;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

// TODO: Create a common KMessageService interface that works for email, sms and push

public interface KEmailService<EMAIL extends KEmail,
        EMAIL_EVENT extends KEmailEvent,
        EMAIL_CONTENT extends KEmailContent,
        EMAIL_ATTACHMENT extends KEmailAttachment>
        extends KService, KEntityService<EMAIL> {

    String getEmailTestDomain();

    EMAIL send(
            String body,
            String subject,
            String from,
            String replyTo,
            String to,
            boolean html,
            List<EMAIL_ATTACHMENT> attachmentList,
            KEmailFooter footer
    ) throws KEmailException;



    void sendRawSMTP(
            String body,
            String subject,
            String from,
            String replyTo,
            String to,
            String cc,
            String bcc,
            boolean html,
            List<EMAIL_ATTACHMENT> attachmentList
    ) throws KEmailException;



    String sendRawAWS(
            String body,
            String subject,
            String from,
            String replyTo,
            String to,
            String cc,
            String bcc,
            boolean html,
            List<EMAIL_ATTACHMENT> mediaList
    ) throws KEmailException;



    String sendRawAWS(
            String from,
            String replyTo,
            String to,
            String subject,
            String textBody,
            String htmlBody,
            List<EMAIL_ATTACHMENT> attachmentList
    ) throws KEmailException;



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
            List<EMAIL_ATTACHMENT> attachmentList
    ) throws KEmailException;



    void deliver(
            Long campaignChannelId,
            Long emailGroupId,
            String fromAddress,
            String replyTo,
            String subject,
            String text,
            String html,
            List<EMAIL_ATTACHMENT> attachmentList,
            KEmailFooter footer,
            Long throttleTime
    ) throws KEmailException;



    void deliver(
            Long campaignChannelId,
            Long emailGroupId,
            String from,
            String replyTo,
            String subject,
            EMAIL_CONTENT content,
            KEmailFooter footer,
            Long throttleTime
    ) throws KEmailException;



    EMAIL deliver(
            String from,
            String replyTo,
            String to,
            String subject,
            String text,
            String html,
            List<EMAIL_ATTACHMENT> attachmentList,
            KEmailFooter footer
    ) throws KEmailException;




    EMAIL deliver(
            String from,
            String replyTo,
            String to,
            String subject,
            EMAIL_CONTENT content,
            KEmailFooter footer
    ) throws KEmailException;



    void processLocalQueue();

    void processSESNotifications();

    EMAIL fetchByUid(String uid);

    EMAIL fetchBySesId(String sesId);

    EMAIL fetchByCampaignChannelIdAndToId(Long campaignChannelId, Long emailAddressId);

    List<EMAIL> fetchByEmailGroupId(Long emailGroupId);

    List<EMAIL> fetchByEmailGroupSlug(String groupSlug);

    List<EMAIL> fetchByCampaignId(Long campaignId);

    List<EMAIL> fetchByCampaignGroupId(Long campaignGroupId);

    List<EMAIL> fetchByCampaignChannelId(Long campaignChannelId);

    KEmailStats calcStats(List<EMAIL> emailList);

    KEmailStats calcStatsByGroupSlug(String groupSlug);

    KEmailStats calcStatsByCampaignId(Long campaignId);

    KEmailStats calcStatsByCampaignGroupId(Long campaignGroupId);

    KEmailStats calcStatsByCampaignChannelId(Long campaignChannelId);

    EMAIL_EVENT addEvent(EMAIL_EVENT event);
}
