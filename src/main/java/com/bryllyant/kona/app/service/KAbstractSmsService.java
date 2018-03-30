package com.bryllyant.kona.app.service;


import com.bryllyant.kona.app.entity.KSms;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public abstract class KAbstractSmsService<SMS extends KSms, SMS_EXAMPLE extends KEntityExample, MAPPER extends KMyBatisMapper<SMS, SMS_EXAMPLE>,USER extends KUser>
        extends KAbstractTwilioService<SMS,SMS_EXAMPLE,MAPPER,USER>
        implements KSmsService<SMS> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractSmsService.class);

    @Override
    public boolean isTestPhoneNumber(String phoneNumber) {
    	List<String> prefixList = getTestPhoneNumberPrefixList();
    	
    	if (prefixList == null) return false;
    	
    	for (String prefix : prefixList) {
    		if (phoneNumber.startsWith(prefix)) {
    			return true;
    		}
    	}
    	
    	return false;
    }
}

