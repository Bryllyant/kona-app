/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.app.dao.AuthCodeMapper;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.AuthCode;
import com.bryllyant.kona.app.entity.AuthCodeExample;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.Registration;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.AuthCodeService;
import com.bryllyant.kona.app.service.KAbstractAuthCodeService;
import com.bryllyant.kona.app.service.RegistrationService;
import com.bryllyant.kona.app.service.ShortUrlService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.app.util.KCallback;
import com.bryllyant.kona.util.KDateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service(AuthCodeService.SERVICE_PATH)
public class AuthCodeServiceImpl
        extends KAbstractAuthCodeService<AuthCode, AuthCodeExample, AuthCodeMapper,User,Registration>
        implements AuthCodeService {

    private static Logger logger = LoggerFactory.getLogger(AuthCodeServiceImpl.class);

    @Autowired
    private AuthCodeMapper authCodeMapper;

    @Autowired
    private KConfig config;

    @Autowired
    private UserService userService;

    @Autowired
    private AppService appService;

    @Autowired
    private ShortUrlService shortUrlService;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    SystemService system;



    @Override @SuppressWarnings("unchecked")
    protected AuthCodeMapper getMapper() {
        return authCodeMapper;
    }



    @Override @SuppressWarnings("unchecked")
    protected UserService getUserService() {
        return userService;
    }



    @Override @SuppressWarnings("unchecked")
    protected RegistrationService getRegistrationService() {
        return registrationService;
    }



    @Override
    protected AuthCode getNewObject() {
        return new AuthCode();
    }



    //system.passwordReset.urlTemplate = http://example.com/account/passsword/{code}
    private String createPasswordResetUrl(String code) {
        String url = config.getString("urlTemplate.system.passwordReset");
        url = url.replaceAll("\\{code\\}", code);
        return url;
    }



    //system.confirmationCode.urlTemplate = http://example.com/system/confirmations/{code}
    private String createEmailConfirmationUrl(String code) {
        String url = config.getString("urlTemplate.system.confirmationCode");
        url = url.replaceAll("\\{code\\}", code);
        return url;
    }



    //system.confirmationCode.urlTemplate = http://example.comm/system/confirmations/{code}
    private String createMobileConfirmationUrl(String code) {
        String url = config.getString("urlTemplate.system.confirmationCode");
        url = url.replaceAll("\\{code\\}", code);
        return url;
    }



    @Override
    protected String getAuthCodeUrl(AuthCode.Type type, Long userId, String code) {
        String url = null;

        switch (type) {
            case EMAIL_CONFIRMATION:
                url = createEmailConfirmationUrl(code);
                break;

            case MOBILE_CONFIRMATION:
                url = createMobileConfirmationUrl(code);
                break;

            case PASSWORD_RESET:
                url = createPasswordResetUrl(code);
                break;

            case PHONE_CONFIRMATION:
                break;

            default:
                break;
        }

        url = shortUrlService.shorten(userId, url);
        return url;
    }



    @Override
    protected void sendAuthCode(AuthCode.Type type, Long userId, String authCodeUrl) {

        switch (type) {
            case EMAIL_CONFIRMATION:
                sendEmailConfirmationEmail(userId, authCodeUrl);
                break;

            case MOBILE_CONFIRMATION:
                sendMobileConfirmationSms(userId, authCodeUrl);
                break;

            case PASSWORD_RESET:
                sendRequestPasswordEmail(userId, authCodeUrl);
                break;

            case PHONE_CONFIRMATION:
                break;

            default:
                break;
        }
    }



    protected void sendRequestPasswordEmail(Long userId, String passwordResetUrl) {
        User user = userService.fetchById(userId);

        if (user.getEmail() == null) {
            logger.info("sendRequestPasswordEmail: User email is null: {$user}");
            return;
        }

        App	app = appService.getSystemApp();

        String templateName = "email.templates.account.passwordReset";

        String subject = "[" + app.getName() + "] ";
        String defaultSubject = "Your Password Reset Request";

        subject += config.getString("email.subject.account.requestPassword", defaultSubject);

        String from = config.getString("system.mail.from");
        String to = user.getEmail();
        String replyTo = from;

        Map<String,Object> params = new HashMap<String,Object>();
        params.put("app", app);
        params.put("user", user);
        params.put("passwordResetUrl", passwordResetUrl);

        system.sendEmail(templateName, params, subject, from, replyTo, to, null, new KCallback<Email>() {
            @Override
            public void success(Email data) {

            }

            @Override
            public void error(Throwable t) {
                logger.error(t.getMessage(), t);
            }
        });


    }



    protected void sendEmailConfirmationEmail(Long userId, String authCodeUrl) {
        User user = userService.fetchById(userId);

        if (user.getEmail() == null) {
            logger.info("sendEmailConfirmationEmail: User email is null: {$user}");
            return;
        }

        App app = appService.getSystemApp();

        String templateName = "email.templates.account.verifyEmail";

        String subject = "[" + app.getName() + "] ";
        String defaultSubject = "Account Confirmation";

        subject += config.getString("email.subject.account.verifyEmail", defaultSubject);

        String from = config.getString("system.mail.from");
        String to = user.getEmail();
        String replyTo = from;

        Map<String,Object> params = new HashMap<String,Object>();
        params.put("app", app);
        params.put("user", user);
        params.put("authCodeUrl", authCodeUrl);

        system.sendEmail(templateName, params, subject, from, replyTo, to, null, new KCallback<Email>() {
            @Override
            public void success(Email data) {

            }

            @Override
            public void error(Throwable t) {
                logger.error("Error sending email: " + t.getMessage(), t);
            }
        });


    }



    protected void sendMobileConfirmationSms(Long userId, String authCodeUrl) {
        User user = userService.fetchById(userId);

        logger.debug("sendMobileConfirmationSms: user:\n" + user);

        if (user.getMobileNumber() == null) {
            logger.info("sendMobileConfirmationSms: User mobile number is null: {$user}");
            return;
        }

        App app = appService.getSystemApp();

        String to = user.getMobileNumber();

        String message = "[" + app.getName() + "]  Your mobile number has been updated. Click to confirm. ";

        message += authCodeUrl;

        try {
            logger.debug("sendMobileConfirmationSms: [" + userId + "]\nto: " + to + "\nmessage:" + message);
            system.sendSms(to, message, null);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }



    @Override
    protected AuthCodeExample getEntityExampleObject() { return new AuthCodeExample(); }




    @Override
    protected Date getAuthCodeExpirationDate(AuthCode.Type type, Long userId) {

        switch(type) {
            case EMAIL_CONFIRMATION:
            case MOBILE_CONFIRMATION:
                return null;

            case PASSWORD_RESET:
                return KDateUtil.addMins(new Date(), 30);

            default:
                return null;
        }
    }


    // explicitly set to null to indicate unlimited use
    @Override
    protected Integer getAuthCodeMaxUseCount(AuthCode.Type type, Long userId) {
        return null;
    }
}
