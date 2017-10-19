/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Sms;
import com.bryllyant.kona.remote.service.KService;

public interface SmsService extends KService, KSmsService<Sms> {
    public static final String SERVICE_PATH = "rpc/SmsService";
    
    public String getFromPhoneNumber();
    
    public String getMessageStatusCallbackUrl();
}
