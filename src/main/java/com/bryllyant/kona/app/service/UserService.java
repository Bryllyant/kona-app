/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import com.bryllyant.kona.remote.service.KServiceClient;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService extends KService, KEntityService<User> {
	String SERVICE_PATH = "rpc/UserService";

    User registerUser(User user, String password, List<String> roles, KServiceClient client, boolean disableWelcomeEmail);

    /**
     * Create an anonymous user with ROLE set to GUEST.
     *
     * Useful when you want to engage a user with your product before they signup.  When they do sign up, be sure to
     * update the ROLE to User.
     *
     * @param client
     * @return user object
     */
    User createGuestUser(User user, KServiceClient client);

    User updatePhoto(User user, Long photoId, String urlPath, String thumbnailUrlPath);

    User updatePhoto(User user, byte[] data, String contentType) throws IOException;

    User removePhoto(User user);

    User fetchByUsername(String username);

    User fetchByEmail(String email);

    User fetchByMobileNumber(String mobileNumber);

    User fetchByAccessToken(String accessToken, boolean validateToken);

    User retire(User user);

    User getSystemUser();

    List<User> fetchByAccountId(Long accountId);

    List<User> fetchProximate(
            Double latitude,
            Double longitude,
            Double radius,
            Date startDate,
            Date endDate);

    List<User> fetchAllRegistered(Map<String,Object> filter); // exclude system, test, etc users

    boolean isUsernameAvailable(String name);

    boolean isEmailVerified(Long userId);

    boolean isMobileNumberVerified(Long userId);

    User setMobileNumberVerified(Long userId);

    User setEmailVerified(Long userId);

    @Transactional
    void addRole(User user, String roleName);

    @Transactional
    void addRoles(User user, List<String> roleNames);

    boolean hasRole(User user, String roleName);

    List<String> getRoles(User user);
}
