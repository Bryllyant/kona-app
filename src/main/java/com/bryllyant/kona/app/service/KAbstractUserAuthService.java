/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.app.entity.KUserAuth;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.encryption.KEncryptUtil;
import com.bryllyant.kona.util.KPassGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

public abstract class KAbstractUserAuthService<USER_AUTH extends KUserAuth, USER_AUTH_EXAMPLE extends KEntityExample, MAPPER extends KMyBatisMapper<USER_AUTH, USER_AUTH_EXAMPLE>,USER extends KUser>
		extends KAbstractService<USER_AUTH,USER_AUTH_EXAMPLE,MAPPER>
		implements KUserAuthService<USER_AUTH,USER> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractUserAuthService.class);

	protected abstract <S extends KUserService<USER>> S getUserService();

	protected abstract USER_AUTH getNewObject();

	protected abstract void sendPasswordResetEmail(USER user, String password);
	

	@Override
	public USER_AUTH fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
	

	@Override
	public USER_AUTH resetPassword(Long userId) {
		String password = KPassGen.generatePassword(8);
		USER_AUTH userAuth = setPlainPassword(userId, password);
		USER user = getUserService().fetchById(userId);
		sendPasswordResetEmail(user, password);
		return userAuth;
	}
	

	@Override 
	public USER_AUTH setEncryptedPassword(Long userId, String encryptedPassword) {
		USER_AUTH userAuth = fetchByUserId(userId);
		if (userAuth == null) {
			userAuth = getNewObject();
			userAuth.setUserId(userId);
			userAuth.setPassword(encryptedPassword);
			userAuth.setCreatedDate(new Date());
			userAuth = add(userAuth);

			// if user didn't have a password, user is most likely
			// not enabled. set user to active once password is set.
			USER user = getUserService().fetchById(userId);
			if (!user.isEnabled()) {
				user.setEnabled(true);
				user = getUserService().update(user);
			}
		} else {
			userAuth.setPassword(encryptedPassword);
			userAuth = update(userAuth);
		}

		return userAuth;
	}
	

	@Override 
	public USER_AUTH setPlainPassword(Long userId, String password) {
		try {
			String passwordx = KEncryptUtil.SHA1(password);
			return setEncryptedPassword(userId, passwordx);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new KAuthException("Error creating encrypted password", KAuthException.Type.INVALID_PASSWORD, e);
		}
	}
	

	@Override
	public USER validateCredentials(String username, String password) throws KAuthException {
		// first check if username is valid
		logger.debug("validating credentials for: "
				+ "username: " + username
				+ "password: " + password);

		USER user = getUserService().fetchByUsername(username);
		if (user == null) {
			throw new KAuthException("Username not found: " + username, KAuthException.Type.INVALID_USERNAME);
		}

		// next check if we have an auth record for this user
		USER_AUTH userAuth = fetchByUserId(user.getId());
		if (userAuth == null || userAuth.getPassword() == null) {
			// if no auth record/password and password is null
			// assume user doesn't require a password
			if (password == null) {
				return user;
			}

			throw new KAuthException("Auth record not found for: " + username, KAuthException.Type.INVALID_PASSWORD);
		}

		// next check if password is valid
		String passwordx;
		try {
			passwordx = KEncryptUtil.SHA1(password);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new KAuthException("Error validating password: " + password, KAuthException.Type.INVALID_PASSWORD);
		}
		
		if (!passwordx.equals(userAuth.getPassword())) {
			throw new KAuthException("Invalid password for username: " + username, KAuthException.Type.INVALID_PASSWORD);
		}

		return user;
	}
	

	@Override
	public void validate(USER_AUTH userAuth) {
    	if (userAuth.getCreatedDate() == null) {
			userAuth.setCreatedDate(new Date());
		}
    	
    	userAuth.setUpdatedDate(new Date());
    	
	}
}
