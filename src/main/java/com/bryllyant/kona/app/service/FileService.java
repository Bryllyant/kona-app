/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.remote.service.KService;

public interface FileService extends KService, KFileService<File> {
	String SERVICE_PATH = "rpc/FileService";
}
