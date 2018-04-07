/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.SmsMapper;
import com.bryllyant.kona.app.entity.Sms;
import com.bryllyant.kona.app.entity.SmsExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.SmsException;
import com.bryllyant.kona.app.service.SmsService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KJsonUtil;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(SmsService.SERVICE_PATH)
public class SmsServiceImpl extends KAbstractService<Sms,SmsExample,SmsMapper>
        implements SmsService {
    
    private static Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

    private final static Integer MAX_MESSAGE_LENGTH = 1600;

    private boolean initialized = false;


    @Autowired
    private KConfig config;
    
    @Autowired
    private SmsMapper smsMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private SystemService system;


    protected String getAccountSid() {
        return config.getString("twilio.accountSid");
    }
    
    protected String getAuthToken() {
        return config.getString("twilio.authToken");
    }
    
    @Override
    public String getMessageStatusCallbackUrl() {
        return config.getString("twilio.messageStatusCallbackUrl");
    }
    

    @Override
    public String getFromPhoneNumber() {
        return config.getString("sms.fromPhoneNumber");
    }
    


    @Override
    public List<String> getTestPhoneNumberPrefixList() {
        List<String> list = new ArrayList<String>();
        
        String prefix = config.getString("sms.testPhoneNumberPrefix");
        
        if (prefix != null) {
            list.add(prefix);
        }
        
        return list;
    }
    
    @Override @SuppressWarnings("unchecked")
    protected SmsMapper getMapper() {
        return smsMapper;
    }
    

    @Override
    public boolean isTestPhoneNumber(String phoneNumber) {
        List<String> prefixList = getTestPhoneNumberPrefixList();

        if (prefixList == null) return false;

        for (String prefix : prefixList) {
            if (phoneNumber.startsWith(prefix)) {
                return true;
            }
        }

        return false;
    }

    protected boolean isSendToSelfEnabled() {
        return false;
    }



    // https://www.twilio.com/docs/api/rest/sending-messages
    protected Integer getMaxMediaCount() {
        return 10;
    }

    // https://www.twilio.com/docs/api/rest/accepted-mime-types
    protected Integer getMaxMessageSize() {
        return 5*1024*1024;
    }



    protected void init() {
        if (!initialized) {
            Twilio.init(getAccountSid(), getAuthToken());
            initialized = true;
        }
    }



    @Override
    public void validate(Sms sms) {
        if (sms.getCreatedDate() == null) {
            sms.setCreatedDate(new Date());
        }

        sms.setUpdatedDate(new Date());

        if (sms.getUid() == null) {
            sms.setUid(uuid());
        }

        if (sms.getClickCount() == null) {
            sms.setClickCount(0);
        }
    }




    @Override
    public Sms fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public Sms fetchByMessageSid(String messageSid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("messageSid", messageSid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

    @Override
    public List<Sms> fetchByCampaignId(Long campaignId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("campaignId", campaignId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    @Override
    public List<Sms> fetchByChannelId(Long channelId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("channelId", channelId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }


    @Override
    public Sms fetchByChannelIdAndToNumber(Long channelId, String toNumber) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("channelId", channelId);
        filter.put("toNumber", toNumber);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    public Sms sendMessage(String to, String body) {
        return sendMessage(getFromPhoneNumber(), to, body, null);
    }

    public Sms sendMessage(String to, String body,  List<String> mediaUrls) {
        return sendMessage(getFromPhoneNumber(), to, body, mediaUrls);
    }

    public Sms sendMessage(String to, String body, String mediaUrl) {
        List<String> urls = new ArrayList<>();
        urls.add(mediaUrl);
        return sendMessage(getFromPhoneNumber(), to, body, urls);
    }

    public Sms sendMessage(String from, String to, String body, List<String> mediaUrls) {
        init();

        if (to == null) {
            throw new SmsException("sendMessage: Message 'to' is null");
        }

        if (to.equals(from) && !isSendToSelfEnabled()) {
            throw new SmsException("sendMessage: Message 'to' equals 'from': " + from);
        }

        List<String> prefixList = getTestPhoneNumberPrefixList();

        if (prefixList != null) {
            for (String testPrefix : prefixList) {
                if (testPrefix != null && to.startsWith(testPrefix)) {
                    logger.warn("sendMessage(): sending message to test account: to: " + to);
                    return null;
                }
            }
        }

        if (body == null) {
            throw new SmsException("sendMessage: Message 'body' is null");
        }


        body = body.trim();

        if (body.length() > MAX_MESSAGE_LENGTH) {
            body = body.substring(0, MAX_MESSAGE_LENGTH);
        }

        MessageCreator creator = Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(from),
                body
        );

        if (mediaUrls != null && mediaUrls.size() > 0) {
            List<URI> uriList = new ArrayList<URI>();

            Integer count = mediaUrls.size();

            if (count > getMaxMediaCount()) {
                count = getMaxMediaCount();
                logger.info("Media messages truncated to max number: " + count);
            }

            for (int i=0; i < count; i++) {
                String mediaUrl = mediaUrls.get(i);
                URI uri = URI.create(mediaUrl);
                uriList.add(uri);
            }

            creator.setMediaUrl(uriList);
        }

        if (getMessageStatusCallbackUrl() != null) {
            creator.setStatusCallback(getMessageStatusCallbackUrl());
        }

        Message message = creator.create();


        //params.add(new BasicNameValuePair("MediaUrl", "http://www.example.com/hearts.png"));


        logger.debug("Message sent: " + message.getBody() + "\n\nMESSAGE SID: " + message.getSid());

        /*
        if (messageStatusCallback != null) {
        	statusQueue.put(message.getSid(), message);
        }
        */


        Sms sms = new Sms();

        sms.setFromNumber(from);
        sms.setToNumber(to);
        sms.setMessage(body);
        sms.setMessageSid(message.getSid());
        sms.setSentDate(new Date());
        sms.setMediaUrls(mediaUrls);

        User u = userService.fetchByMobileNumber(to);

        if (u != null) {
            sms.setToUserId(u.getId());
        }

        return save(sms);
    }



    @Override
    public void processMessageStatus(Map<String,Object> map) {
        logger.debug("processMessageStatus: got result: " + KJsonUtil.toJson(map));

        // https://www.twilio.com/docs/api/twiml/sms/twilio_request#request-parameters
        // https://www.twilio.com/docs/api/rest/sending-messages
        // https://www.twilio.com/docs/api/rest/message#sms-status-values

        String messageSid = (String) map.get("MessageSid");
        String messageStatus = (String) map.get("MessageStatus");
        String errorCode = (String) map.get("ErrorCode");
        String errorMessage = (String) map.get("ErrorMessage");

        Sms sms = fetchByMessageSid(messageSid);

        if (sms == null) {
            logger.info("Sms message not found for messageSid: " + messageSid);
            return;
        }

        if (messageStatus != null) {
            sms.setStatus(messageStatus);

            switch (messageStatus) {
                case "accepted":
                case "queued":
                case "sending":
                case "receiving":
                case "received":
                    return;

                case "sent":
                case "delivered":
                    sms.setDelivered(true);
                    break;

                case "undelivered":
                case "failed":
                    sms.setFailed(true);
                    break;
            }
        }

        if (errorCode != null) {
            if (errorMessage == null) {
                errorMessage = "";
            }

            switch (errorCode) {
                case "30001":
                    errorMessage += "Queue overflow";
                    break;

                case "30002":
                    errorMessage += "Account suspended";
                    break;

                case "30003":
                    errorMessage += "Unreachable destination handset";
                    break;

                case "30004":
                    errorMessage += "Message blocked";
                    break;

                case "30005":
                    errorMessage += "Unknown destination handset";
                    break;

                case "30006":
                    errorMessage += "Landline or unreachable carrier";
                    break;

                case "30007":
                    errorMessage += "Carrier violation";
                    break;

                case "30008":
                    errorMessage += "Unknown error";
                    break;

                case "30009":
                    errorMessage += "Missing segment";
                    break;

                case "30010":
                    errorMessage += "Message price exceeds max price";
                    break;
            }

            sms.setErrorCode(errorCode);
            sms.setErrorMessage(errorMessage);
        }

        // save status;
        save(sms);

    }
}
