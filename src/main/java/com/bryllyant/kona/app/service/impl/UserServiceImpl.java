/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.dao.UserMapper;
import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.AppUser;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.Invitation;
import com.bryllyant.kona.app.entity.Media;
import com.bryllyant.kona.app.entity.Position;
import com.bryllyant.kona.app.entity.Registration;
import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.entity.UserAuth;
import com.bryllyant.kona.app.entity.UserExample;
import com.bryllyant.kona.app.service.AccountService;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.AppUserService;
import com.bryllyant.kona.app.service.EntityNameRuleService;
import com.bryllyant.kona.app.service.InvitationService;
import com.bryllyant.kona.app.service.KAbstractUserService;
import com.bryllyant.kona.app.service.KEmailException;
import com.bryllyant.kona.app.service.MediaService;
import com.bryllyant.kona.app.service.PositionService;
import com.bryllyant.kona.app.service.RegistrationService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.TokenService;
import com.bryllyant.kona.app.service.UserAuthService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.app.util.KUtil;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service(UserService.SERVICE_PATH)
public class UserServiceImpl 
		extends KAbstractUserService<User,UserExample,
									 UserAuth,
									 Media,
									 Account,
									 File,
									 AppUser,
									 Registration,
									 Invitation,
									 Token,
									 Position> 
		implements UserService {
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserMapper userDao;
	
	@Autowired
	private KConfig config;
	
	@Autowired
	private AppService appService;
	
	@Autowired
	private TokenService tokenService;
    
	@Autowired
	private UserAuthService userAuthService;

	@Autowired
	private MediaService mediaService;
    
	@Autowired
	private RegistrationService registrationService;
    
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private InvitationService invitationService;

	@Autowired
	private PositionService positionService;
	
	@Autowired
	EntityNameRuleService entityNameRuleService;
	
	@Autowired
	SystemService system;
	

	// ----------------------------------------------------------------------------
	
	protected Long getDefaultAppId() {
		return system.getSystemApp().getId();
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public boolean isUsernameAvailable(String name) {
	    // usernames cannot be null or be empty string
        if (name == null || name.length() == 0) return false;

        // usernames can only contain [a-zA-Z_0-9]
        if (!name.matches("^\\w+$")) return false;

        // check if username violates any known rules
        if (!entityNameRuleService.isAcceptable(name)) return false;


        // finally check if username already exists
        boolean isUnique = false;
        
        User user = fetchByUsername(name);
        
        if (user == null) {
            isUnique = true;
        }
        
        return isUnique;
	}
	
	// ----------------------------------------------------------------------------
    
	@Override
	protected User getNewObject() {
		return new User();
	}

	// ----------------------------------------------------------------------------

	@Override
	protected String generateUid() {
        return KUtil.uuid();
	}
	
	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected  AccountService getAccountService() {
        return accountService;
	}
	
	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected  TokenService getTokenService() {
        return tokenService;
	}
	
	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected UserAuthService getUserAuthService() {
        return userAuthService;
	}
	
	// ----------------------------------------------------------------------------
	@Override @SuppressWarnings("unchecked")
    protected MediaService getMediaService() {
        return mediaService;
    }
	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected AppUserService getAppUserService() {
        return appUserService;
	}
	
	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected RegistrationService getRegistrationService() {
        return registrationService;
	}
	
	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected InvitationService getInvitationService() {
		return invitationService;
	}
	
	// ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected PositionService getPositionService() {
        return positionService;
    }
    
	// ----------------------------------------------------------------------------

	@Override
	protected void sendRegisteredUserEmail(Long appId, User user) {
		
		boolean sendWelcomeEmail = config.getBoolean("system.registration.sendWelcomeEmail", true);
		
		if (!sendWelcomeEmail) {
			logger.debug("sendRegisteredUserEmail: system.registration.sendWelcomeEmail set to false");
			return;
		}
		
		if (user.getEmail() == null) {
			logger.info("sendRegisteredUserEmail: User email is null: {$user}");
			return;
		}

		App app = appService.fetchById(appId);

		String from = config.getString("system.mail.from");
		String to = user.getEmail();
		String replyTo = from;

        String subject = "[" + app.getName() + "] ";
        String defaultSubject = "Welcome";

        subject += config.getString("email.subject.account.welcomeEmail", defaultSubject);

		String templateName = "email.templates.account.welcomeEmail";

		Map<String,Object> params = new HashMap<String,Object>();
		params.put("user", user);
		params.put("app", app);
		
		try {
			system.sendEmail(templateName, params, subject, from, replyTo, to, null);
		} catch (KEmailException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected UserMapper getDao() {
		return userDao;
	}
	
	// ----------------------------------------------------------------------------

	@Override
	protected UserExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
			Map<String, Object> filter, boolean distinct) {
		UserExample example = new UserExample();

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


}
