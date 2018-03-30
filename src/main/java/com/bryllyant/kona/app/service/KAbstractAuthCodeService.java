/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KAuthCode;
import com.bryllyant.kona.app.entity.KRegistration;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.bryllyant.kona.app.entity.KAuthCode.Type.EMAIL_CONFIRMATION;
import static com.bryllyant.kona.app.entity.KAuthCode.Type.MOBILE_CONFIRMATION;
import static com.bryllyant.kona.app.entity.KAuthCode.Type.PASSWORD_RESET;

public abstract class KAbstractAuthCodeService<AUTH_CODE extends KAuthCode, AUTH_CODE_EXAMPLE extends KEntityExample, MAPPER extends KMyBatisMapper<AUTH_CODE, AUTH_CODE_EXAMPLE>,
											   USER extends KUser,
											   REGISTRATION extends KRegistration>
		extends KAbstractService<AUTH_CODE,AUTH_CODE_EXAMPLE,MAPPER>
		implements KAuthCodeService<AUTH_CODE> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractAuthCodeService.class);
    

    protected abstract AUTH_CODE getNewObject();

    protected abstract <S extends KUserService<USER>> S getUserService();

    protected abstract <S extends KRegistrationService<REGISTRATION,USER>> S getRegistrationService();

	protected abstract String getAuthCodeUrl(AUTH_CODE.Type type, Long userId, String code);
    
    protected abstract void sendAuthCode(AUTH_CODE.Type type, Long userId, String authCodeUrl);
    
    protected abstract Date getAuthCodeExpirationDate(AUTH_CODE.Type type, Long userId);
    

    protected Integer getAuthCodeMaxUseCount(AUTH_CODE.Type type, Long userId) {
        return null;
    }


	protected String generateAccessCode() {
		return uuid();
	}
    

	@Override
	public void validate(AUTH_CODE authCode) {
    	if (authCode.getCreatedDate() == null) {
			authCode.setCreatedDate(new Date());
		}

    	authCode.setUpdatedDate(new Date());

		if (authCode.getUseCount() == null) {
			authCode.setUseCount(0);
		}
	}


	@Override
	public AUTH_CODE fetchByCode(String code) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("code", code);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
	

	@Override
	public List<AUTH_CODE> expireByUserId(Long userId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		filter.put("confirmedDate", null);
        
		List<AUTH_CODE> result = fetchByCriteria(0, 99999, null, filter, false);
        
		List<AUTH_CODE> expiredList = new ArrayList<>();
        
		for (AUTH_CODE authCode : result) {
			authCode.setValid(false);
			
			authCode.setExpirationDate(new Date());
            
			authCode = update(authCode);
            
			expiredList.add(authCode);
		}
        
		return expiredList;
	}
	

	private List<AUTH_CODE> fetchByUserIdAndType(Long userId, AUTH_CODE.Type type) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		
        if (type != null) {
        	filter.put("type", type);
        }
        
		return fetchByCriteria(0, 99999, null, filter, false);
	}
    

	@Override
	public AUTH_CODE accessCode(String code) {
		if (code == null) return null;
        
		AUTH_CODE authCode = fetchByCode(code);
		
		if (authCode == null || !isActive(authCode)) return null;
		
		Integer useCount = authCode.getUseCount() + 1;
        
		authCode.setUseCount(useCount);
        
		authCode.setLastAccessedDate(new Date());

		if (authCode.getConfirmedDate() == null) {
		    authCode.setConfirmedDate(new Date());
        }

        // check type 
        //String type = getAuthCodeType(authCode.getTypeId());
        
        REGISTRATION registration = null;
        
        if (authCode.getType() == EMAIL_CONFIRMATION) {
            registration = getRegistrationService().fetchByUserId(authCode.getUserId());
            registration.setEmailPending(false);
            registration.setEmailVerified(true);
            getRegistrationService().update(registration);
        } else if (authCode.getType() == MOBILE_CONFIRMATION) {
            registration = getRegistrationService().fetchByUserId(authCode.getUserId());
            registration.setMobilePending(false);
            registration.setMobileVerified(true);
            getRegistrationService().update(registration);
        } else if (authCode.getType() == PASSWORD_RESET) {

        }
            
		return update(authCode);
	}


	// check to see if a (valid) authCode code has already been sent to this user
	// if so and resend flag is set, invalidate existing authCodes and return true. 
	private boolean canSendAuthCode(AUTH_CODE.Type type, Long userId, boolean resend) {
		List<AUTH_CODE> authCodes = fetchByUserIdAndType(userId, type);

		if (authCodes == null || authCodes.size()==0) return true;
        
		boolean haveActive = false;
        
		for (AUTH_CODE authCode : authCodes) {
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
	public void requestAuthCode(AUTH_CODE.Type type, Long userId, boolean resend) {

        logger.debug("requestAuthCode: userId:\n" + userId);

        if (!canSendAuthCode(type, userId, resend)) return;

        String authCodeUrl = generateAuthCodeUrl(type, userId);

		sendAuthCode(type, userId, authCodeUrl);
	}

	@Override
	public String generateAuthCodeUrl(AUTH_CODE.Type type, Long userId) {
        String code = generateAuthCode(type, userId);

        String authCodeUrl = getAuthCodeUrl(type, userId, code);

        REGISTRATION registration = null;

        if (type == EMAIL_CONFIRMATION) {
            registration = getRegistrationService().fetchByUserId(userId);
            registration.setEmailPending(true);
            registration.setEmailVerified(false);
            getRegistrationService().update(registration);
        } else if (type == MOBILE_CONFIRMATION) {
            registration = getRegistrationService().fetchByUserId(userId);
            registration.setMobilePending(true);
            registration.setMobileVerified(false);
            getRegistrationService().update(registration);
        } else if (type == PASSWORD_RESET) {
        }

        return authCodeUrl;
    }



	@Override 
	public void requestAuthCodes(List<AUTH_CODE.Type> types, Long userId, boolean resend) {
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					for (AUTH_CODE.Type type : types) {
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
		

	
	protected String generateAuthCode(AUTH_CODE.Type type, Long userId) {
		String code = generateAccessCode();

		Date now = new Date();
		
		AUTH_CODE authCode = getNewObject();
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


    
	protected boolean isActive(AUTH_CODE authCode) {
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
