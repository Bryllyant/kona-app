/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KDevice;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;


public interface KDeviceService<DEVICE extends KDevice> extends KService, KEntityService<DEVICE> {

    public DEVICE fetchByUid(String uid);

    DEVICE fetchByAdvertiserId(String advertiserId);

    DEVICE fetchByDeviceUuid(String deviceUuid);
}
