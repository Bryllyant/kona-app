/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.AuthCodeMapper;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.AuthCode;
import com.bryllyant.kona.app.entity.AuthCodeExample;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.Registration;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.AuthCodeService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.RegistrationService;
import com.bryllyant.kona.app.service.ShortUrlService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.util.Callback;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KDateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(AuthCodeService.SERVICE_PATH)
public class AuthCodeServiceImpl
        extends KAbstractService<AuthCode, AuthCodeExample, AuthCodeMapper>
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


    protected String generateAccessCode() {
        return uuid();
    }


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

        system.sendEmail(templateName, params, subject, from, replyTo, to, null, new Callback<Email>() {
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

        system.sendEmail(templateName, params, subject, from, replyTo, to, null, new Callback<Email>() {
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
    protected Integer getAuthCodeMaxUseCount(AuthCode.Type type, Long userId) {
        return null;
    }


    @Override
    public void validate(AuthCode authCode) {
        if (authCode.getCreatedDate() == null) {
            authCode.setCreatedDate(new Date());
        }

        authCode.setUpdatedDate(new Date());

        if (authCode.getUseCount() == null) {
            authCode.setUseCount(0);
        }
    }


    @Override
    public AuthCode fetchByCode(String code) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("code", code);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }


    @Override
    public List<AuthCode> expireByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        filter.put("confirmedDate", null);

        List<AuthCode> result = fetchByCriteria(filter);

        List<AuthCode> expiredList = new ArrayList<>();

        for (AuthCode authCode : result) {
            authCode.setValid(false);

            authCode.setExpirationDate(new Date());

            authCode = update(authCode);

            expiredList.add(authCode);
        }

        return expiredList;
    }


    private List<AuthCode> fetchByUserIdAndType(Long userId, AuthCode.Type type) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);

        if (type != null) {
            filter.put("type", type);
        }

        return fetchByCriteria(filter);
    }


    @Override
    public AuthCode accessCode(String code) {
        if (code == null) return null;

        AuthCode authCode = fetchByCode(code);

        if (authCode == null || !isActive(authCode)) return null;

        Integer useCount = authCode.getUseCount() + 1;

        authCode.setUseCount(useCount);

        authCode.setLastAccessedDate(new Date());

        if (authCode.getConfirmedDate() == null) {
            authCode.setConfirmedDate(new Date());
        }

        // check type
        //String type = getAuthCodeType(authCode.getTypeId());

        Registration registration = null;

        if (authCode.getType() == AuthCode.Type.EMAIL_CONFIRMATION) {
            registration = registrationService.fetchByUserId(authCode.getUserId());
            registration.setEmailPending(false);
            registration.setEmailVerified(true);
            registrationService.update(registration);
        } else if (authCode.getType() == AuthCode.Type.MOBILE_CONFIRMATION) {
            registration = registrationService.fetchByUserId(authCode.getUserId());
            registration.setMobilePending(false);
            registration.setMobileVerified(true);
            registrationService.update(registration);
        } else if (authCode.getType() == AuthCode.Type.PASSWORD_RESET) {

        }

        return update(authCode);
    }


    // check to see if a (valid) authCode code has already been sent to this user
    // if so and resend flag is set, invalidate existing authCodes and return true.
    private boolean canSendAuthCode(AuthCode.Type type, Long userId, boolean resend) {
        List<AuthCode> authCodes = fetchByUserIdAndType(userId, type);

        if (authCodes == null || authCodes.size()==0) return true;

        boolean haveActive = false;

        for (AuthCode authCode : authCodes) {
            if (isActive(authCode)) {
                haveActive = true;
                if (resend) {
                    authCode.setValid(false);
                    update(authCode);
                }
            }
        }

        if (!haveActive) return true;

        if (haveActive && resend) return true;

        return false;
    }



    @Override
    public void requestAuthCode(AuthCode.Type type, Long userId, boolean resend) {

        logger.debug("requestAuthCode: userId:\n" + userId);

        if (!canSendAuthCode(type, userId, resend)) return;

        String authCodeUrl = generateAuthCodeUrl(type, userId);

        sendAuthCode(type, userId, authCodeUrl);
    }

    @Override
    public String generateAuthCodeUrl(AuthCode.Type type, Long userId) {
        String code = generateAuthCode(type, userId);

        String authCodeUrl = getAuthCodeUrl(type, userId, code);

        Registration registration = null;

        if (type == AuthCode.Type.EMAIL_CONFIRMATION) {
            registration = registrationService.fetchByUserId(userId);
            registration.setEmailPending(true);
            registration.setEmailVerified(false);
            registrationService.update(registration);
        } else if (type == AuthCode.Type.MOBILE_CONFIRMATION) {
            registration = registrationService.fetchByUserId(userId);
            registration.setMobilePending(true);
            registration.setMobileVerified(false);
            registrationService.update(registration);
        } else if (type == AuthCode.Type.PASSWORD_RESET) {
        }

        return authCodeUrl;
    }



    @Override
    public void requestAuthCodes(List<AuthCode.Type> types, Long userId, boolean resend) {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    for (AuthCode.Type type : types) {
                        logger.debug("calling requestAuthCode for type: " + type);
                        requestAuthCode(type, userId, resend);
                    }
                } catch (Exception e) {
                    logger.error("Error processing verification requests for userId: " + userId, e);
                }
            }
        };
        t.start();

    }



    protected String generateAuthCode(AuthCode.Type type, Long userId) {
        String code = generateAccessCode();

        Date now = new Date();

        AuthCode authCode = new AuthCode();
        authCode.setType(type);
        authCode.setUserId(userId);
        authCode.setCode(code);
        authCode.setUseCount(0);
        authCode.setCreatedDate(now);
        authCode.setValid(true);

        Integer maxUseCount = getAuthCodeMaxUseCount(type, userId);
        authCode.setMaxUseCount(maxUseCount);

        Date expirationDate = getAuthCodeExpirationDate(type, userId);
        //authCode.setExpirationDate(KDateUtil.addMins(now, 30)); // expire in 30 mins

        authCode.setExpirationDate(expirationDate);

        add(authCode);
        return code;
    }



    protected boolean isActive(AuthCode authCode) {
        if (!authCode.isValid()) {
            logger.debug("authCode is not valid: " + authCode);
            return false;
        }

        Integer useCount = authCode.getUseCount();

        if (authCode.getMaxUseCount() != null && useCount >= authCode.getMaxUseCount()) {
            logger.debug("authCode useCount exceeds maxUseCount: " + useCount);
            return false;
        }

        Date expirationDate = authCode.getExpirationDate();

        Date now = new Date();
        if (expirationDate != null && now.getTime() > expirationDate.getTime()) {
            logger.debug("authCode expired: " + expirationDate);
            return false;
        }

        logger.debug("authCode: {}  isActive: {}", authCode, true);

        return true;
    }
}
