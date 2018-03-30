/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KAccount;
import com.bryllyant.kona.app.entity.KApp;
import com.bryllyant.kona.app.entity.KEmail;
import com.bryllyant.kona.app.entity.KFile;
import com.bryllyant.kona.app.entity.KPush;
import com.bryllyant.kona.app.entity.KSms;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.app.util.KCallback;
import com.bryllyant.kona.remote.service.KService;
import org.apache.commons.configuration.Configuration;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface KSystemService<
    APP extends KApp,
    ACCOUNT extends KAccount,
    USER extends KUser,
    FILE extends KFile,
    EMAIL extends KEmail,
    SMS extends KSms,
    PUSH extends KPush>
extends KService {

    Configuration getConfig(Long appId);


    FILE toFile(byte[] data, String contentType, String filename);

    FILE toFile(
        USER user,
        byte[] data,
        String contentType,
        String filename
    );


    FILE toFile(
        USER user,
        byte[] data,
        String contentType,
        String filename,
        String srcFilename,
        String srcHostname,
        boolean tempFile
    );


    FILE saveUrlToFile(String url) throws IOException;


    FILE saveUrlToFile(USER user, String url) throws IOException;


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
            boolean html, List<FILE> attachments
            ) throws KEmailException;


    void sendRawEmail(
            String templateName, 
            Map<String,Object> params, 
            String subject, 
            String from,
            String replyTo, 
            String to, 
            String cc, 
            String bcc, 
            List<FILE> attachments
            ) throws KEmailException;


    void sendEmail(
            String body, 
            String subject, 
            String from, 
            String replyTo,
            String to, 
            boolean html, 
            List<FILE> attachments,
            KCallback<EMAIL> callback
            ) throws KEmailException;


    void sendEmail(
            String templateName, 
            Map<String,Object> params, 
            String subject, 
            String from,
            String replyTo, 
            String to, 
            List<FILE> attachments,
            KCallback<EMAIL> callback
            ) throws KEmailException;


    void sendEmail(
            String templateName, 
            Map<String,Object> params, 
            String subject, 
            String to,
            KCallback<EMAIL> callback
            ) throws KEmailException;


    void sendSms(String to, String body, KCallback<SMS> callback) throws KSmsException;

    void sendSms(String to, String body, String mediaUrl, KCallback<SMS> callback);

    void sendSms(String to, String body, List<String> mediaUrls, KCallback<SMS> callback);

    void sendSms(String to, String body, List<String> mediaUrls, Long delay,  KCallback<SMS> callback);

    void sendPush(Long userId, String title, String message, String imageUrl, String actionUrl, boolean sandbox, KCallback<PUSH> callback);

    void alert(String subject, Throwable t);

    void alert(String subject, String body);

    void alert(String subject, String body, Throwable t);

    void alert(String subject, String body, boolean html);

    APP getSystemApp();

    ACCOUNT getSystemAccount();

    USER getSystemUser();
}
