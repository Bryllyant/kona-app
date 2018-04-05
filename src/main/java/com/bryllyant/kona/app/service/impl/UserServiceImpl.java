/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.UserMapper;
import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.AuthRole;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.Media;
import com.bryllyant.kona.app.entity.Position;
import com.bryllyant.kona.app.entity.Registration;
import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.entity.UserExample;
import com.bryllyant.kona.app.entity.UserRole;
import com.bryllyant.kona.app.service.AccountService;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.AppUserService;
import com.bryllyant.kona.app.service.AuthException;
import com.bryllyant.kona.app.service.AuthRoleService;
import com.bryllyant.kona.app.service.EntityNameRuleService;
import com.bryllyant.kona.app.service.InvitationService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.service.KServiceException;
import com.bryllyant.kona.app.service.MediaService;
import com.bryllyant.kona.app.service.PositionService;
import com.bryllyant.kona.app.service.RegistrationService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.TokenService;
import com.bryllyant.kona.app.service.UserAuthService;
import com.bryllyant.kona.app.service.UserRoleService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.locale.KValidator;
import com.bryllyant.kona.remote.service.KServiceClient;
import com.bryllyant.kona.util.AppUtil;
import com.bryllyant.kona.util.Callback;
import com.bryllyant.kona.util.KStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

@Service(UserService.SERVICE_PATH)
public class UserServiceImpl
        extends KAbstractService<User,UserExample,UserMapper>
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

    @Autowired
    AppUtil appUtil;

    @Override @SuppressWarnings("unchecked")
    protected UserMapper getMapper() {
        return userMapper;
    }


    protected Long getDefaultAppId() {
        return system.getSystemApp().getId();
    }

    protected AuthRole getGuestRole() {
        return authRoleService.fetchByName("GUEST");
    }

    protected List<AuthRole> getDefaultRoles() {
        AuthRole role = authRoleService.fetchByName("User");

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
            throw new AuthException(
                    "Username is null or empty: " + name,
                    AuthException.Type.INVALID_USERNAME
            );
        }

        // An email can be a valid username
        if (KValidator.isEmail(name)) {
            return true;
        }

        // if not email, usernames can only contain [a-zA-Z_0-9]
        if (!name.matches("^\\w+$")) {
            throw new AuthException(
                    "Non-email usernames can only contain [a-zA-Z_0-9]: " + name,
                    AuthException.Type.INVALID_USERNAME
            );
        }

        // check if username violates any known rules
        if (!entityNameRuleService.isAcceptable(name)) {
            throw new AuthException(
                    "Username contains blacklisted words or phrases : " + name,
                    AuthException.Type.INVALID_USERNAME
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


    protected String generateUid() {
        return appUtil.uuid();
    }


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

    protected boolean autoUpdateDisplayName() {
        return true;
    }

    protected User.Type getDefaultType() {
        return User.Type.User;
    }

    protected User.Presence getDefaultPresence() {
        return User.Presence.OFFLINE;
    }

    protected String getDefaultLocale() {
        return Locale.US.toLanguageTag();
    }

    protected String getDefaultTimeZone() {
        return TimeZone.getTimeZone("America/New_York").getID();
    }


    protected void checkUserExists(String username, String email) {
        if (username == null) {
            throw new AuthException("Username is null", AuthException.Type.INVALID_USERNAME);
        }

        if (!isUsernameAvailable(username)) {
            throw new AuthException("Username not available: " + username);
        }

        if (email != null) {
            User user = fetchByEmail(email);

            if (user != null) {
                throw new AuthException("Email exists: " + email);
            }
        }
    }


    @Override
    @Transactional
    public User createGuestUser(KServiceClient client) {
        User user = new User();

        List<String> roles = new ArrayList<>();

        AuthRole role = getGuestRole();

        if (role != null) {
            roles.add(role.getName());
        }

        return registerUser(user, null, roles, client, true);
    }


    @Override @Transactional
    public User registerUser(User user, String password, List<String> roles, KServiceClient client, boolean disableWelcomeEmail) {

        if (user.getUid() == null) {
            user.setUid(generateUid());
        }

        if (user.getUsername() == null) {
            user.setUsername(user.getUid());
        }

        // Check if user is valid so far
        checkUserExists(user.getUsername(), user.getEmail());


        if (user.getType() == null) {
            user.setType(getDefaultType());
        }

        if (roles == null || roles.size() == 0) {
            List<AuthRole> defaultRoles = getDefaultRoles();

            if (defaultRoles != null) {

                if (roles == null) roles = new ArrayList<>();

                for (AuthRole role : defaultRoles) {
                    roles.add(role.getName());
                }
            }
        }


        if (user.getPresence() == null) {
            user.setPresence(getDefaultPresence());
        }

        if (user.getTimeZone() == null) {
            user.setTimeZone(getDefaultTimeZone());
        }

        if (user.getLocale() == null) {
            user.setLocale(getDefaultLocale());
        }

        String displayName = KStringUtil.createFullName(user.getFirstName(), user.getLastName());
        if (displayName == null || displayName.length() == 0) {
            displayName = user.getUsername();
        }

        user.setDisplayName(displayName);

        user.setCreatedDate(new Date());

        user.setEnabled(true);

        Account account = null;

        if (user.getAccountId() == null) {
            account = accountService.createAccount(null);
            user.setAccountId(account.getId());
        } else {
            account = accountService.fetchById(user.getAccountId());
        }

        user = add(user);

        if (account.getOwnerId() == null) {
            account.setOwnerId(user.getId());
            accountService.update(account);
        }


        // update auth record
        if (password != null) {
            userAuthService.setPlainPassword(user.getId(), password);
        }

        addRoles(user, roles);

        if (client == null) {
            client = new KServiceClient();
        }

        if (client.getHostname() == null) {
            client.setHostname("127.0.0.1"); // hostname cannot be null in registration table
        }

        if (client.getAppId() == null) {
            client.setAppId(getDefaultAppId());
        }

        // log registration record
        Long signupTime = null;
        registrationService.createRegistration(user, client, signupTime);

        // create AppUser record
        App app = appService.fetchById(client.getAppId());

        if (app.getType() != App.Type.INTERNAL) {
            appUserService.create(client.getAppId(), user.getId(), null, null);
        }

        // process invitations sent for this user
        invitationService.processNewUserInvitations(user.getId());

        // this overrides config setting
        if (!disableWelcomeEmail) {
            sendRegisteredUserEmail(client.getAppId(), user);
        }

        return user;
    }


    @Override
    @Transactional
    public User retire(User user) {
        // fetch fresh object
        user = fetchById(user.getId());

        // return user if already retired
        if (user == null || user.getDeletedDate() != null) {
            return user;
        }

        // NOTE: we need uuid here in case multiple apps with the same name are deleted.
        // first app called test is deleted, then second app created called test gets deleted.
        String prefix = "$RETIRED_" + uuid() + "_";
        user.setUsername(prefix + user.getUsername());
        user.setMobileNumber(prefix + user.getMobileNumber());
        user.setEmail(prefix + user.getEmail());
        user.setEnabled(false);
        user.setDeletedDate(new Date());
        user = update(user);


        // retiring the account may fail if there are other non-retired
        // users associated with the account.
        // Since account will check active users, it's imperative that
        // this object be updated first.  otherwise AccountService will
        // see this user as active and fail to retire the account.
        if (user.getParentId() == null) {
            Account account = accountService.fetchById(user.getAccountId());

            try {
                accountService.retire(account);
            } catch (Throwable t) {
            }
        }

        return user;
    }
    @Override @Transactional
    public void addRole(User user, String roleName) {
        List<String> roleNames = new ArrayList<>();
        roleNames.add(roleName);
        addRoles(user, roleNames);
    }

    @Override @Transactional
    public void addRoles(User user, List<String> roleNames) {
        for (String roleName : roleNames) {
            AuthRole role = authRoleService.fetchByName(roleName);

            if (role == null) {
                throw new KServiceException("Auth Role not found for name: " + roleName);
            }

            userRoleService.create(user, role);
        }

        tokenService.expireByUserId(user.getId());
    }


    @Override
    public boolean hasRole(User user, String roleName) {
        AuthRole role = authRoleService.fetchByName(roleName);

        if (role == null) {
            throw new KServiceException("Auth Role not found for name: " + roleName);
        }


        UserRole userRole = userRoleService.fetchByUserIdAndRoleId(user.getId(), role.getId());

        if (userRole == null) {
            return false;
        }

        return true;
    }

    @Override
    public List<String> getRoles(User user) {
        List<UserRole> userRoles = userRoleService.fetchByUserId(user.getId());

        List<String> result = new ArrayList<>();

        for (UserRole userRole : userRoles) {
            AuthRole role = authRoleService.fetchById(userRole.getRoleId());
            result.add(role.getName());
        }

        return result;
    }


    @Transactional
    protected UserRole addUserRole(User user, AuthRole role) {
        UserRole userRole = userRoleService.create(user, role);
        tokenService.expireByUserId(user.getId());
        return userRole;
    }


    @Override
    @Transactional(readOnly = true)
    public User fetchByUsername(String username) {
        logger.debug("UserServiceImpl: fetchByUsername: username: {}", username);
        Map<String, Object> filter = KMyBatisUtil.createFilter("username", username);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
    @Transactional(readOnly = true)
    public User fetchByUid(String uid) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
    @Transactional(readOnly = true)
    public User fetchByEmail(String email) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("email", email);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
    @Transactional(readOnly = true)
    public User fetchByMobileNumber(String mobileNumber) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("mobileNumber", mobileNumber);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
    @Transactional(readOnly = true)
    public List<User> fetchByAccountId(Long accountId) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }


    @Override
    @Transactional(readOnly = true)
    public User fetchByAccessToken(String accessToken, boolean validateToken) {
        User user = null;

        Token token = tokenService.fetchByAccessToken(accessToken, validateToken);

        if (token != null && token.getUserId() != null) {
            user = fetchById(token.getUserId());
        }

        return user;
    }



    @Override
    @Transactional
    public User updatePhoto(User user, Long photoId, String urlPath, String thumbnailUrlPath) {
        // NOTE: photoId references UserMedia object

        user.setPhotoId(photoId);
        user.setPhotoUrl(urlPath);
        user.setThumbnailUrl(thumbnailUrlPath);

        return update(user);
    }


    @Override
    @Transactional
    public User removePhoto(User user) {
        if (user.getPhotoId() != null) {
            mediaService.removeById(user.getPhotoId());
        }

        user.setPhotoId(null);
        user.setPhotoUrl(null);
        user.setThumbnailUrl(null);

        return update(user);
    }


    @Override
    @Transactional
    public User updatePhoto(User user, byte[] data, String contentType) throws IOException {
        String name = "user-photo-" + user.getUid();

        Media media = mediaService.add(user, name, data, contentType);

        return updatePhoto(user, media.getId(), media.getUrlPath(), media.getThumbnailUrlPath());
    }


    @Override
    @Transactional
    public void remove(User user) {
        if (user.getPhotoId() != null) {
            mediaService.removeById(user.getPhotoId());
        }

        super.remove(user);
    }


    @Override
    public void validate(User user) {
        if (user.getUid() == null) {
            user.setUid(generateUid());
        }

        if (user.getUsername() == null) {
            user.setUsername(user.getUid());
        }

        if (user.getType() == null) {
            user.setType(getDefaultType());
        }

        if (user.getPresence() == null) {
            user.setPresence(getDefaultPresence());
        }

        if (autoUpdateDisplayName()) {
            String displayName = KStringUtil.createFullName(user.getFirstName(), user.getLastName());
            if (displayName == null || displayName.length() == 0) {
                displayName = user.getUsername();
            }

            user.setDisplayName(displayName);
        }

        if (user.getTimeZone() == null) {
            user.setTimeZone(getDefaultTimeZone());
        }

        if (user.getLocale() == null) {
            user.setLocale(getDefaultLocale());
        }

        if (user.getCreatedDate() == null) {
            user.setCreatedDate(new Date());
        }

        user.setUpdatedDate(new Date());
    }


    @Override
    public boolean isEmailVerified(Long userId) {
        Registration registration = registrationService.fetchByUserId(userId);
        if (registration == null) return false;
        return registration.isEmailVerified();

    }


    @Override
    public boolean isMobileNumberVerified(Long userId) {
        Registration registration = registrationService.fetchByUserId(userId);
        if (registration == null) return false;
        return registration.isMobileVerified();

    }



    @Override
    @Transactional
    public User setMobileNumberVerified(Long userId) {
        Registration registration = registrationService.fetchByUserId(userId);

        if (registration == null) {
            throw new IllegalArgumentException("Registration not found for userId: " + userId);
        }


        registration.setMobileVerified(true);

        registrationService.update(registration);

        return fetchById(userId);
    }


    @Override
    @Transactional
    public User setEmailVerified(Long userId) {
        Registration registration = registrationService.fetchByUserId(userId);

        if (registration == null) {
            throw new IllegalArgumentException("Registration not found for userId: " + userId);
        }


        registration.setEmailVerified(true);

        registrationService.update(registration);

        return fetchById(userId);
    }


    @Override
    @Transactional(readOnly = true)
    public List<User> fetchAllRegistered(Map<String, Object> filter) {
        if (filter == null) {
            filter = new HashMap<String, Object>();
        }

        //List<Registration> registrations = registrationService.fetchAll

        filter.put("type", User.Type.User);

        //FIXME: fetch users containing specified roles
        //filter.put("roles", UserRole.User.getId());

        return fetchByCriteria(0, 99999, null, filter, false);
    }


    public List<User> fetchProximate(
            Double latitude,
            Double longitude,
            Double radius,
            Date startDate,
            Date endDate
    ) {

        List<Position> positions = positionService.fetchProximate(
                latitude,
                longitude,
                radius,
                startDate,
                endDate,
                null
        );

        List<User> result = new ArrayList<>(positions.size());

        for (Position position : positions) {
            User user = fetchById(position.getUserId());

            if (user != null) {
                result.add(user);
            }

        }

        return result;
    }
}
