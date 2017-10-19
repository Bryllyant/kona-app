/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.entity.UserDevice;
import com.bryllyant.kona.remote.service.KService;

public interface UserDeviceService extends KService, KUserDeviceService<UserDevice,User,Device> {
	public static final String SERVICE_PATH = "rpc/UserDeviceService";
	
}
