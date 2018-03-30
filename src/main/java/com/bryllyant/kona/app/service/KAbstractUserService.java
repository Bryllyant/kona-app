/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KAccount;
import com.bryllyant.kona.app.entity.KApp;
import com.bryllyant.kona.app.entity.KAppUser;
import com.bryllyant.kona.app.entity.KAuthRole;
import com.bryllyant.kona.app.entity.KFile;
import com.bryllyant.kona.app.entity.KInvitation;
import com.bryllyant.kona.app.entity.KMedia;
import com.bryllyant.kona.app.entity.KPosition;
import com.bryllyant.kona.app.entity.KRegistration;
import com.bryllyant.kona.app.entity.KToken;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.app.entity.KUserAuth;
import com.bryllyant.kona.app.entity.KUserRole;
import com.bryllyant.kona.app.util.KUtil;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.remote.service.KServiceClient;
import com.bryllyant.kona.util.KStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public abstract class KAbstractUserService<
        USER extends KUser,
        USER_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<USER, USER_EXAMPLE>,
        USER_AUTH extends KUserAuth,
        AUTH_ROLE extends KAuthRole,
        USER_ROLE extends KUserRole,
        MEDIA extends KMedia,
        ACCOUNT extends KAccount,
        FILE extends KFile,
        APP extends KApp,
        APP_USER extends KAppUser,
        REGISTRATION extends KRegistration,
        INVITATION extends KInvitation,
        TOKEN extends KToken,
        POSITION extends KPosition>
        extends KAbstractService<USER, USER_EXAMPLE,MAPPER>
        implements KUserService<USER> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractUserService.class);


    protected abstract USER getNewObject();

    protected abstract <S extends KAccountService<ACCOUNT>> S getAccountService();

    protected abstract <S extends KUserAuthService<USER_AUTH, USER>> S getUserAuthService();

    protected abstract <S extends KUserRoleService<USER_ROLE, USER, AUTH_ROLE>> S getUserRoleService();

    protected abstract <S extends KAuthRoleService<AUTH_ROLE>> S getAuthRoleService();

    protected abstract <S extends KMediaService<MEDIA, USER, FILE>> S getMediaService();

    protected abstract <S extends KAppService<APP>> S getAppService();

    protected abstract <S extends KAppUserService<APP_USER>> S getAppUserService();

    protected abstract <S extends KRegistrationService<REGISTRATION, USER>> S getRegistrationService();

    protected abstract <S extends KTokenService<TOKEN>> S getTokenService();

    protected abstract <S extends KInvitationService<INVITATION>> S getInvitationService();

    protected abstract <S extends KPositionService<POSITION>> S getPositionService();

    protected abstract void sendRegisteredUserEmail(Long appId, USER user);

    protected abstract Long getDefaultAppId();

    protected abstract AUTH_ROLE getGuestRole();

    protected abstract List<AUTH_ROLE> getDefaultRoles();


    protected String generateUid() {
        return KUtil.uuid();
    }

    protected boolean autoUpdateDisplayName() {
        return true;
    }

    protected USER.Type getDefaultType() {
        return USER.Type.USER;
    }

    protected USER.Presence getDefaultPresence() {
        return USER.Presence.OFFLINE;
    }

    protected String getDefaultLocale() {
        return Locale.US.toLanguageTag();
    }

    protected String getDefaultTimeZone() {
        return TimeZone.getTimeZone("America/New_York").getID();
    }


    protected void checkUserExists(String username, String email) {
        if (username == null) {
            throw new KAuthException("Username is null", KAuthException.Type.INVALID_USERNAME);
        }

        if (!isUsernameAvailable(username)) {
            throw new KAuthException("Username not available: " + username);
        }

        if (email != null) {
            USER user = fetchByEmail(email);

            if (user != null) {
                throw new KAuthException("Email exists: " + email);
            }
        }
    }


    @Override
    @Transactional
    public USER createGuestUser(KServiceClient client) {
        USER user = getNewObject();

        List<String> roles = new ArrayList<>();

        AUTH_ROLE role = getGuestRole();

        if (role != null) {
            roles.add(role.getName());
        }

        return registerUser(user, null, roles, client, true);
    }


    @Override @Transactional
    public USER registerUser(USER user, String password, List<String> roles, KServiceClient client, boolean disableWelcomeEmail) {

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
            List<AUTH_ROLE> defaultRoles = getDefaultRoles();

            if (defaultRoles != null) {

                if (roles == null) roles = new ArrayList<>();

                for (AUTH_ROLE role : defaultRoles) {
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

        ACCOUNT account = null;

        if (user.getAccountId() == null) {
            account = getAccountService().createAccount(null);
            user.setAccountId(account.getId());
        } else {
            account = getAccountService().fetchById(user.getAccountId());
        }

        user = add(user);

        if (account.getOwnerId() == null) {
            account.setOwnerId(user.getId());
            getAccountService().update(account);
        }


        // update auth record
        if (password != null) {
            getUserAuthService().setPlainPassword(user.getId(), password);
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
        getRegistrationService().createRegistration(user, client, signupTime);

        // create AppUser record
        APP app = getAppService().fetchById(client.getAppId());

        if (app.getType() != APP.Type.INTERNAL) {
            getAppUserService().create(client.getAppId(), user.getId(), null, null);
        }

        // process invitations sent for this user
        getInvitationService().processNewUserInvitations(user.getId());

        // this overrides config setting
        if (!disableWelcomeEmail) {
            sendRegisteredUserEmail(client.getAppId(), user);
        }

        return user;
    }


    @Override
    @Transactional
    public USER retire(USER user) {
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
            ACCOUNT account = getAccountService().fetchById(user.getAccountId());

            try {
                getAccountService().retire(account);
            } catch (Throwable t) {
            }
        }

        return user;
    }
    @Override @Transactional
    public void addRole(USER user, String roleName) {
        List<String> roleNames = new ArrayList<>();
        roleNames.add(roleName);
        addRoles(user, roleNames);
    }

    @Override @Transactional
    public void addRoles(USER user, List<String> roleNames) {
        for (String roleName : roleNames) {
            AUTH_ROLE role = getAuthRoleService().fetchByName(roleName);

            if (role == null) {
                throw new KServiceException("Auth Role not found for name: " + roleName);
            }

            getUserRoleService().create(user, role);
        }

        getTokenService().expireByUserId(user.getId());
    }


    @Override
    public boolean hasRole(USER user, String roleName) {
        AUTH_ROLE role = getAuthRoleService().fetchByName(roleName);

        if (role == null) {
            throw new KServiceException("Auth Role not found for name: " + roleName);
        }


        USER_ROLE userRole = getUserRoleService().fetchByUserIdAndRoleId(user.getId(), role.getId());

        if (userRole == null) {
            return false;
        }

        return true;
    }

    @Override
    public List<String> getRoles(USER user) {
        List<USER_ROLE> userRoles = getUserRoleService().fetchByUserId(user.getId());

        List<String> result = new ArrayList<>();

        for (USER_ROLE userRole : userRoles) {
            AUTH_ROLE role = getAuthRoleService().fetchById(userRole.getRoleId());
            result.add(role.getName());
        }

        return result;
    }


    @Transactional
    protected USER_ROLE addUserRole(USER user, AUTH_ROLE role) {
        USER_ROLE userRole = getUserRoleService().create(user, role);
        getTokenService().expireByUserId(user.getId());
        return userRole;
    }


    @Override
    @Transactional(readOnly = true)
    public USER fetchByUsername(String username) {
        logger.debug("UserServiceImpl: fetchByUsername: username: {}", username);
        Map<String, Object> filter = KMyBatisUtil.createFilter("username", username);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
    @Transactional(readOnly = true)
    public USER fetchByUid(String uid) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
    @Transactional(readOnly = true)
    public USER fetchByEmail(String email) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("email", email);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
    @Transactional(readOnly = true)
    public USER fetchByMobileNumber(String mobileNumber) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("mobileNumber", mobileNumber);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
    @Transactional(readOnly = true)
    public List<USER> fetchByAccountId(Long accountId) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }


    @Override
    @Transactional(readOnly = true)
    public USER fetchByAccessToken(String accessToken, boolean validateToken) {
        USER user = null;

        TOKEN token = getTokenService().fetchByAccessToken(accessToken, validateToken);

        if (token != null && token.getUserId() != null) {
            user = fetchById(token.getUserId());
        }

        return user;
    }



    @Override
    @Transactional
    public USER updatePhoto(USER user, Long photoId, String urlPath, String thumbnailUrlPath) {
        // NOTE: photoId references UserMedia object

        user.setPhotoId(photoId);
        user.setPhotoUrl(urlPath);
        user.setThumbnailUrl(thumbnailUrlPath);

        return update(user);
    }


    @Override
    @Transactional
    public USER removePhoto(USER user) {
        if (user.getPhotoId() != null) {
            getMediaService().removeById(user.getPhotoId());
        }

        user.setPhotoId(null);
        user.setPhotoUrl(null);
        user.setThumbnailUrl(null);

        return update(user);
    }


    @Override
    @Transactional
    public USER updatePhoto(USER user, byte[] data, String contentType) throws IOException {
        String name = "user-photo-" + user.getUid();

        MEDIA media = getMediaService().add(user, name, data, contentType);

        return updatePhoto(user, media.getId(), media.getUrlPath(), media.getThumbnailUrlPath());
    }


    @Override
    @Transactional
    public void remove(USER user) {
        if (user.getPhotoId() != null) {
            getMediaService().removeById(user.getPhotoId());
        }

        super.remove(user);
    }


    @Override
    public void validate(USER user) {
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
        REGISTRATION registration = getRegistrationService().fetchByUserId(userId);
        if (registration == null) return false;
        return registration.isEmailVerified();

    }


    @Override
    public boolean isMobileNumberVerified(Long userId) {
        REGISTRATION registration = getRegistrationService().fetchByUserId(userId);
        if (registration == null) return false;
        return registration.isMobileVerified();

    }



    @Override
    @Transactional
    public USER setMobileNumberVerified(Long userId) {
        REGISTRATION registration = getRegistrationService().fetchByUserId(userId);

        if (registration == null) {
            throw new IllegalArgumentException("Registration not found for userId: " + userId);
        }


        registration.setMobileVerified(true);

        getRegistrationService().update(registration);

        return fetchById(userId);
    }


    @Override
    @Transactional
    public USER setEmailVerified(Long userId) {
        REGISTRATION registration = getRegistrationService().fetchByUserId(userId);

        if (registration == null) {
            throw new IllegalArgumentException("Registration not found for userId: " + userId);
        }


        registration.setEmailVerified(true);

        getRegistrationService().update(registration);

        return fetchById(userId);
    }


    @Override
    @Transactional(readOnly = true)
    public List<USER> fetchAllRegistered(Map<String, Object> filter) {
        if (filter == null) {
            filter = new HashMap<String, Object>();
        }

        //List<REGISTRATION> registrations = getRegistrationService().fetchAll

        filter.put("type", USER.Type.USER);

        //FIXME: fetch users containing specified roles
        //filter.put("roles", UserRole.USER.getId());

        return fetchByCriteria(0, 99999, null, filter, false);
    }


    public List<USER> fetchProximate(
            Double latitude,
            Double longitude,
            Double radius,
            Date startDate,
            Date endDate
    ) {

        List<POSITION> positions = getPositionService().fetchProximate(
                latitude,
                longitude,
                radius,
                startDate,
                endDate,
                null
        );

        List<USER> result = new ArrayList<>(positions.size());

        for (POSITION position : positions) {
            USER user = fetchById(position.getUserId());

            if (user != null) {
                result.add(user);
            }

        }

        return result;
    }

}
