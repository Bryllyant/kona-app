/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.Push;
import com.bryllyant.kona.app.entity.Sms;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.remote.service.KService;
import com.bryllyant.kona.util.Callback;
import org.apache.commons.configuration.Configuration;

import java.io.IOException;
import java.util.List;
import java.util.Map;

;

public interface SystemService extends KService {
	String SERVICE_PATH = "rpc/SystemService";

    Configuration getConfig(Long appId);


    File toFile(byte[] data, String contentType, String filename);

    File toFile(
            User user,
            byte[] data,
            String contentType,
            String filename
    );


    File toFile(
            User user,
            byte[] data,
            String contentType,
            String filename,
            String srcFilename,
            String srcHostname,
            boolean tempFile
    );


    File saveUrlToFile(String url) throws IOException;


    File saveUrlToFile(User user, String url) throws IOException;


    boolean isTestPhoneNumber(String phoneNumber);


    boolean isTestLoginCode(String code);


    String formatPhoneNumber(String phoneNumber);


    void sendRawEmail(
            String body,
            String subject,
            String from,
            String replyTo,
            String to,
            String cc,
            String bcc,
            boolean html, List<File> attachments
    ) throws EmailException;


    void sendRawEmail(
            String templateName,
            Map<String,Object> params,
            String subject,
            String from,
            String replyTo,
            String to,
            String cc,
            String bcc,
            List<File> attachments
    ) throws EmailException;


    void sendEmail(
            String body,
            String subject,
            String from,
            String replyTo,
            String to,
            boolean html,
            List<File> attachments,
            Callback<Email> callback
    ) throws EmailException;


    void sendEmail(
            String templateName,
            Map<String,Object> params,
            String subject,
            String from,
            String replyTo,
            String to,
            List<File> attachments,
            Callback<Email> callback
    ) throws EmailException;


    void sendEmail(
            String templateName,
            Map<String,Object> params,
            String subject,
            String to,
            Callback<Email> callback
    ) throws EmailException;


    void sendSms(String to, String body, Callback<Sms> callback) throws SmsException;

    void sendSms(String to, String body, String mediaUrl, Callback<Sms> callback);

    void sendSms(String to, String body, List<String> mediaUrls, Callback<Sms> callback);

    void sendSms(String to, String body, List<String> mediaUrls, Long delay,  Callback<Sms> callback);

    void sendPush(Long userId, String title, String message, String imageUrl, String actionUrl, boolean sandbox, Callback<Push> callback);

    void alert(String subject, Throwable t);

    void alert(String subject, String body);

    void alert(String subject, String body, Throwable t);

    void alert(String subject, String body, boolean html);

    App getSystemApp();

    Account getSystemAccount();

    User getSystemUser();
}
