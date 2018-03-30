/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import com.bryllyant.kona.remote.service.KServiceClient;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The client side stub for the RPC service.
 */
public interface KUserService<USER extends KUser> extends KService, KEntityService<USER> {
	USER registerUser(USER user, String password, List<String> roles, KServiceClient client, boolean disableWelcomeEmail);

	/**
	 * Create an anonymous user with ROLE set to GUEST.
	 * 
	 * Useful when you want to engage a user with your product before they signup.  When they do sign up, be sure to
	 * update the ROLE to USER.
	 * 
	 * @param client
	 * @return user object
	 */
	USER createGuestUser(KServiceClient client);
	
	USER updatePhoto(USER user, Long photoId, String urlPath, String thumbnailUrlPath);

	USER updatePhoto(USER user, byte[] data, String contentType) throws IOException;

    USER removePhoto(USER user);

    USER fetchByUid(String uid);

    USER fetchByUsername(String username);
    
    USER fetchByEmail(String email);
    
    USER fetchByMobileNumber(String mobileNumber);

    USER fetchByAccessToken(String accessToken, boolean validateToken);
    
	USER retire(USER user);

    USER getSystemUser();

	List<USER> fetchByAccountId(Long accountId);
	
	List<USER> fetchProximate(
	        Double latitude, 
	        Double longitude, 
	        Double radius, 
	        Date startDate, 
	        Date endDate);
	
	List<USER> fetchAllRegistered(Map<String,Object> filter); // exclude system, test, etc users
	
	boolean isUsernameAvailable(String name);

	boolean isEmailVerified(Long userId);

	boolean isMobileNumberVerified(Long userId);
    
	USER setMobileNumberVerified(Long userId);
    
	USER setEmailVerified(Long userId);

	@Transactional
	void addRole(USER user, String roleName);

	@Transactional
	void addRoles(USER user, List<String> roleNames);

	boolean hasRole(USER user, String roleName);

    List<String> getRoles(USER user);
}
