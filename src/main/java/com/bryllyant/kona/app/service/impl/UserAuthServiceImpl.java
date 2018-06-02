/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.UserAuthMapper;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.entity.UserAuth;
import com.bryllyant.kona.app.entity.UserAuthExample;
import com.bryllyant.kona.app.service.AuthException;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.UserAuthService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.encryption.KEncryptUtil;
import com.bryllyant.kona.util.KPassGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

@Service(UserAuthService.SERVICE_PATH)
public class UserAuthServiceImpl 
		extends KAbstractService<UserAuth,UserAuthExample,UserAuthMapper>
		implements UserAuthService {
	
	private static Logger logger = LoggerFactory.getLogger(UserAuthServiceImpl.class);

	@Autowired
	private UserAuthMapper userAuthMapper;
	
	@Autowired
	private UserService userService;
	
	
	@Override @SuppressWarnings("unchecked")
	protected UserAuthMapper getMapper() {
		return userAuthMapper;
	}
    

	protected void sendPasswordResetEmail(User user, String password) {
		// TODO Auto-generated method stub
	}


    @Override
    public UserAuth fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }


    @Override
    public UserAuth resetPassword(Long userId) {
        String password = KPassGen.generatePassword(8);
        UserAuth userAuth = setPlainPassword(userId, password);
        User user = userService.fetchById(userId);
        sendPasswordResetEmail(user, password);
        return userAuth;
    }


    @Override
    public UserAuth setEncryptedPassword(Long userId, String encryptedPassword) {
        UserAuth userAuth = fetchByUserId(userId);
        if (userAuth == null) {
            userAuth = new UserAuth();
            userAuth.setUserId(userId);
            userAuth.setPassword(encryptedPassword);
            userAuth.setCreatedDate(new Date());
            userAuth = add(userAuth);

            // if user didn't have a password, user is most likely
            // not enabled. set user to active once password is set.
            User user = userService.fetchById(userId);
            if (!user.isEnabled()) {
                user.setEnabled(true);
                user = userService.update(user);
            }
        } else {
            userAuth.setPassword(encryptedPassword);
            userAuth = update(userAuth);
        }

        return userAuth;
    }


    @Override
    public UserAuth setPlainPassword(Long userId, String password) {
        try {
            String passwordx = KEncryptUtil.SHA1(password);
            return setEncryptedPassword(userId, passwordx);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new AuthException("Error creating encrypted password", AuthException.Type.INVALID_PASSWORD, e);
        }
    }


    @Override
    public User validateCredentials(String username, String password) throws AuthException {
        // first check if username is valid
        logger.debug("validating credentials for: "
                + "username: " + username
                + "password: " + password);

        User user = userService.fetchByUsername(username);
        if (user == null) {
            throw new AuthException("Username not found: " + username, AuthException.Type.INVALID_USERNAME);
        }

        // next check if we have an auth record for this user
        UserAuth userAuth = fetchByUserId(user.getId());
        if (userAuth == null || userAuth.getPassword() == null) {
            // if no auth record/password and password is null
            // assume user doesn't require a password
            if (password == null) {
                return user;
            }

            throw new AuthException("Auth record not found for: " + username, AuthException.Type.INVALID_PASSWORD);
        }

        // next check if password is valid
        String passwordx;
        try {
            passwordx = KEncryptUtil.SHA1(password);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new AuthException("Error validating password: " + password, AuthException.Type.INVALID_PASSWORD);
        }

        if (!passwordx.equals(userAuth.getPassword())) {
            throw new AuthException("Invalid password for username: " + username, AuthException.Type.INVALID_PASSWORD);
        }

        return user;
    }


    @Override
    public void validate(UserAuth userAuth) {
        if (userAuth.getCreatedDate() == null) {
            userAuth.setCreatedDate(new Date());
        }

        userAuth.setUpdatedDate(new Date());

        if (userAuth.getUid() == null) {
            userAuth.setUid(uuid());
        }

    }
}
