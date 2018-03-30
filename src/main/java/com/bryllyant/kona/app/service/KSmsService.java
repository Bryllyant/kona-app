package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KSms;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;
import java.util.Map;

public interface KSmsService<SMS extends KSms> extends KService, KEntityService<SMS> {
    
    SMS fetchByUid(String uid);

    SMS fetchByMessageSid(String messageSid);

    List<SMS> fetchByCampaignId(Long campaignId);

    List<SMS> fetchByChannelId(Long campaignChannelId);

    SMS fetchByChannelIdAndToNumber(Long campaignChannelId, String toNumber);
    
    SMS sendMessage(String to, String body);
    
    SMS sendMessage(String to, String body, String mediaUrl);

    SMS sendMessage(String to, String body, List<String> mediaUrls);
    
    SMS sendMessage(String from, String to, String body, List<String> mediaUrls);

//    void deliver(
//            Long campaignId,
//            Long channelId,
//            Long groupId,
//            String from,
//            MESSAGE_CONTENT content,
//            Long throttleTime
//    ) throws KSmsException;

    String getFromPhoneNumber();

    List<String> getTestPhoneNumberPrefixList();
    
    boolean isTestPhoneNumber(String phoneNumber);

    void processMessageStatus(Map<String, Object> map);
}
