/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Setting;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.remote.service.KService;

public interface SettingService extends KService, KSettingService<Setting,User> {
    public static final String SERVICE_PATH = "rpc/SettingService";
}
