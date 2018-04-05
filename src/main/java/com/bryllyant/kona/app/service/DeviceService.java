/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

public interface DeviceService extends KService, KEntityService<Device> {
	String SERVICE_PATH = "rpc/DeviceService";

    Device fetchByAdvertiserId(String advertiserId);

    Device fetchByDeviceUuid(String deviceUuid);
	
}
