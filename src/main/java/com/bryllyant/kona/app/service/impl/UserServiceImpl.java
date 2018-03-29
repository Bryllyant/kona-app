/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.dao.UserMapper;
import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.AppUser;
import com.bryllyant.kona.app.entity.AuthRole;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.Invitation;
import com.bryllyant.kona.app.entity.Media;
import com.bryllyant.kona.app.entity.Position;
import com.bryllyant.kona.app.entity.Registration;
import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.entity.UserAuth;
import com.bryllyant.kona.app.entity.UserExample;
import com.bryllyant.kona.app.entity.UserRole;
import com.bryllyant.kona.app.service.AccountService;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.AppUserService;
import com.bryllyant.kona.app.service.AuthRoleService;
import com.bryllyant.kona.app.service.EntityNameRuleService;
import com.bryllyant.kona.app.service.InvitationService;
import com.bryllyant.kona.app.service.KAbstractUserService;
import com.bryllyant.kona.app.service.KAuthException;
import com.bryllyant.kona.app.service.MediaService;
import com.bryllyant.kona.app.service.PositionService;
import com.bryllyant.kona.app.service.RegistrationService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.TokenService;
import com.bryllyant.kona.app.service.UserAuthService;
import com.bryllyant.kona.app.service.UserRoleService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.app.util.KCallback;
import com.bryllyant.kona.app.util.KUtil;
import com.bryllyant.kona.locale.KValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(UserService.SERVICE_PATH)
public class UserServiceImpl
        extends KAbstractUserService<User, UserExample, UserMapper,
        UserAuth,
        AuthRole,
        UserRole,
        Media,
        Account,
        File,
        App,
        AppUser,
        Registration,
        Invitation,
        Token,
        Position>
        implements UserService {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

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
    private UserRoleService userRoleService;

    @Autowired
    private AuthRoleService authRoleService;

    @Autowired
    EntityNameRuleService entityNameRuleService;

    @Autowired
    SystemService system;


    protected Long getDefaultAppId() {
        return system.getSystemApp().getId();
    }

    @Override
    protected AuthRole getGuestRole() {
        return authRoleService.fetchByName("GUEST");
    }

    @Override
    protected List<AuthRole> getDefaultRoles() {
        AuthRole role = authRoleService.fetchByName("USER");

        List<AuthRole> roles = new ArrayList<>();

        roles.add(role);

        return roles;
    }

    @Override
    public User getSystemUser() {
        String username = config.getString("system.username");
        logger.debug("system username: {}", username);
        return fetchByUsername(username);
    }

    @Override
    public boolean isUsernameAvailable(String name) {
        // usernames cannot be null or be empty string
        if (name == null || name.length() == 0) {
            throw new KAuthException(
                    "Username is null or empty: " + name,
                    KAuthException.Type.INVALID_USERNAME
            );
        }

        // An email can be a valid username
        if (KValidator.isEmail(name)) {
            return true;
        }

        // if not email, usernames can only contain [a-zA-Z_0-9]
        if (!name.matches("^\\w+$")) {
            throw new KAuthException(
                    "Non-email usernames can only contain [a-zA-Z_0-9]: " + name,
                    KAuthException.Type.INVALID_USERNAME
            );
        }

        // check if username violates any known rules
        if (!entityNameRuleService.isAcceptable(name)) {
            throw new KAuthException(
                    "Username contains blacklisted words or phrases : " + name,
                    KAuthException.Type.INVALID_USERNAME
            );
        }

        // finally check if username already exists
        boolean isUnique = false;

        User user = fetchByUsername(name);

        if (user == null) {
            isUnique = true;
        }

        return isUnique;
    }


    @Override
    protected User getNewObject() {
        return new User();
    }


    @Override
    protected String generateUid() {
        return KUtil.uuid();
    }


    @Override @SuppressWarnings("unchecked")
    protected  AccountService getAccountService() {
        return accountService;
    }

    @Override @SuppressWarnings("unchecked")
    protected  UserRoleService getUserRoleService() {
        return userRoleService;
    }

    @Override @SuppressWarnings("unchecked")
    protected  AuthRoleService getAuthRoleService() {
        return authRoleService;
    }


    @Override @SuppressWarnings("unchecked")
    protected  TokenService getTokenService() {
        return tokenService;
    }

    @Override @SuppressWarnings("unchecked")
    protected UserAuthService getUserAuthService() {
        return userAuthService;
    }

    @Override @SuppressWarnings("unchecked")
    protected MediaService getMediaService() {
        return mediaService;
    }

    @Override @SuppressWarnings("unchecked")
    protected AppService getAppService() {
        return appService;
    }

    @Override @SuppressWarnings("unchecked")
    protected AppUserService getAppUserService() {
        return appUserService;
    }

    @Override @SuppressWarnings("unchecked")
    protected RegistrationService getRegistrationService() {
        return registrationService;
    }

    @Override @SuppressWarnings("unchecked")
    protected InvitationService getInvitationService() {
        return invitationService;
    }


    @Override @SuppressWarnings("unchecked")
    protected PositionService getPositionService() {
        return positionService;
    }


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

        Map<String,Object> params = new HashMap<>();
        params.put("user", user);
        params.put("app", app);

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

    @Override @SuppressWarnings("unchecked")
    protected UserMapper getMapper() {
        return userMapper;
    }
}
