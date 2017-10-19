/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.remote.service.KService;

public interface DeviceService extends KService, KDeviceService<Device> {
	public static final String SERVICE_PATH = "rpc/DeviceService";
	
}
