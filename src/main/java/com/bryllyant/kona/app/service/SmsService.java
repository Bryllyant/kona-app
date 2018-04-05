/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Sms;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;
import java.util.Map;

public interface SmsService extends KService, KEntityService<Sms> {
    String SERVICE_PATH = "rpc/SmsService";
    
    String getFromPhoneNumber();
    
    String getMessageStatusCallbackUrl();

    Sms fetchByMessageSid(String messageSid);

    List<Sms> fetchByCampaignId(Long campaignId);

    List<Sms> fetchByChannelId(Long campaignChannelId);

    Sms fetchByChannelIdAndToNumber(Long campaignChannelId, String toNumber);

    Sms sendMessage(String to, String body);

    Sms sendMessage(String to, String body, String mediaUrl);

    Sms sendMessage(String to, String body, List<String> mediaUrls);

    Sms sendMessage(String from, String to, String body, List<String> mediaUrls);

//    void deliver(
//            Long campaignId,
//            Long channelId,
//            Long groupId,
//            String from,
//            MESSAGE_CONTENT content,
//            Long throttleTime
//    ) throws SmsException;


    List<String> getTestPhoneNumberPrefixList();

    boolean isTestPhoneNumber(String phoneNumber);

    void processMessageStatus(Map<String, Object> map);
}
