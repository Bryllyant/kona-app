/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.dao.AuthCodeMapper;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.AuthCode;
import com.bryllyant.kona.app.entity.AuthCodeExample;
import com.bryllyant.kona.app.entity.KAuthCodeType;
import com.bryllyant.kona.app.entity.Registration;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.AuthCodeService;
import com.bryllyant.kona.app.service.KAbstractAuthCodeService;
import com.bryllyant.kona.app.service.KEmailException;
import com.bryllyant.kona.app.service.RegistrationService;
import com.bryllyant.kona.app.service.ShortUrlService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
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
		extends KAbstractAuthCodeService<AuthCode,AuthCodeExample,User,Registration> 
		implements AuthCodeService {
	
	private static Logger logger = LoggerFactory.getLogger(AuthCodeServiceImpl.class);

	@Autowired
	private AuthCodeMapper authCodeDao;
    
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
    
	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected AuthCodeMapper getDao() {
		return authCodeDao;
	}
    
	// ----------------------------------------------------------------------------
    
	@Override @SuppressWarnings("unchecked")
	protected UserService getUserService() {
		return userService;
	}
    
	// ----------------------------------------------------------------------------
    
	@Override @SuppressWarnings("unchecked")
	protected RegistrationService getRegistrationService() {
		return registrationService;
	}
    
	// ----------------------------------------------------------------------------
    
	@Override
	protected AuthCode getNewObject() {
		return new AuthCode();
	}
    
	// ----------------------------------------------------------------------------

	//system.passwordReset.urlTemplate = http://example.com/account/passsword/{code}
	private String createPasswordResetUrl(String code) {
		String url = config.getString("urlTemplate.system.passwordReset");
		url = url.replaceAll("\\{code\\}", code);
		return url;
	}
    
	// ----------------------------------------------------------------------------
    
	//system.confirmationCode.urlTemplate = http://example.com/system/confirmations/{code}
	private String createEmailConfirmationUrl(String code) {
		String url = config.getString("urlTemplate.system.confirmationCode");
		url = url.replaceAll("\\{code\\}", code);
		return url;
	}
    
	// ----------------------------------------------------------------------------
    
	//system.confirmationCode.urlTemplate = http://example.comm/system/confirmations/{code}
	private String createMobileConfirmationUrl(String code) {
		String url = config.getString("urlTemplate.system.confirmationCode");
		url = url.replaceAll("\\{code\\}", code);
		return url;
	}
    
	// ----------------------------------------------------------------------------
	
	@Override
	protected String getAuthCodeUrl(Long typeId, Long appId, Long userId, String code) {
        String url = null;
        
		KAuthCodeType type = KAuthCodeType.getInstance(typeId);
        
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
        
        url = shortUrlService.shorten(appId, userId, url);
		return url;
	}
    
	// ----------------------------------------------------------------------------
    
	@Override
	protected void sendAuthCode(Long typeId, Long appId, Long userId, String authCodeUrl) {
		KAuthCodeType type = KAuthCodeType.getInstance(typeId);

		switch (type) {
		case EMAIL_CONFIRMATION:
			sendEmailConfirmationEmail(appId, userId, authCodeUrl);
			break;
            
		case MOBILE_CONFIRMATION:
			sendMobileConfirmationSms(appId, userId, authCodeUrl);
			break;

		case PASSWORD_RESET:
            sendRequestPasswordEmail(appId, userId, authCodeUrl);
			break;

		case PHONE_CONFIRMATION:
			break;

		default:
			break;
		}
	}
    
	// ----------------------------------------------------------------------------
    
	protected void sendRequestPasswordEmail(Long appId, Long userId, String passwordResetUrl) {
        User user = userService.fetchById(userId);
        
		if (user.getEmail() == null) {
			logger.info("sendRequestPasswordEmail: User email is null: {$user}");
			return;
		}

		App app = null;
		if (appId == null) {
			app = appService.getSystemApp();
		} else {
			app = appService.fetchById(appId);
		}

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
		
		try {
			system.sendEmail(templateName, params, subject, from, replyTo, to, null);
		} catch (KEmailException e) {
			logger.error(e.getMessage(), e);
		}
		
	}
    
	// ----------------------------------------------------------------------------
    
	protected void sendEmailConfirmationEmail(Long appId, Long userId, String authCodeUrl) {
        User user = userService.fetchById(userId);
        
		if (user.getEmail() == null) {
			logger.info("sendEmailConfirmationEmail: User email is null: {$user}");
			return;
		}

		App app = null;
		
		if (appId == null) {
			app = appService.getSystemApp();
		} else {
			app = appService.fetchById(appId);
		}

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
		
		try {
			system.sendEmail(templateName, params, subject, from, replyTo, to, null);
		} catch (KEmailException e) {
			logger.error("Error sending email: " + e.getMessage(), e);
		}
		
	}
	
	// ----------------------------------------------------------------------------
    
	protected void sendMobileConfirmationSms(Long appId, Long userId, String authCodeUrl) {
		User user = userService.fetchById(userId);
        
        logger.debug("sendMobileConfirmationSms: user:\n" + user);
        
    	if (user.getMobileNumber() == null) {
			logger.info("sendMobileConfirmationSms: User mobile number is null: {$user}");
			return;
		}

    	App app = null;
		if (appId == null) {
			app = appService.getSystemApp();
		} else {
			app = appService.fetchById(appId);
		}
        
        
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
    
	// ----------------------------------------------------------------------------
	
	@Override
	protected AuthCodeExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
			Map<String, Object> filter, boolean distinct) {
		AuthCodeExample example = new AuthCodeExample();

		if (sortOrder != null) {
			example.setOrderByClause(KMyBatisUtil.getOrderByString(sortOrder));
		}

		if (startRow == null) startRow = 0;
		if (resultSize == null) resultSize = 99999999;

        example.setOffset(startRow);
        example.setLimit(resultSize);
		example.setDistinct(distinct);

		KMyBatisUtil.buildExample(example.or().getClass(), example.or(), filter);
		
		return example;
	}

	// ----------------------------------------------------------------------------
	
	@Override
	protected Date getAuthCodeExpirationDate(Long typeId, Long appId, Long userId) {
		KAuthCodeType type = KAuthCodeType.getInstance(typeId);
		
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

	// ----------------------------------------------------------------------------
	
	// explicitly set to null to indicate unlimited use
	
	@Override
	protected Integer getAuthCodeMaxUseCount(Long typeId, Long appId, Long userId) {
	    return null;
	}
}
