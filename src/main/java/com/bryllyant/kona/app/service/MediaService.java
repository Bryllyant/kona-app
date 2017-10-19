/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.Media;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.remote.service.KService;

public interface MediaService extends KService, KMediaService<Media,User,File> {
	public static final String SERVICE_PATH = "rpc/MediaService";
	
}
