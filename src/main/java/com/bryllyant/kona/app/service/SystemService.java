/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.remote.service.KService;

public interface SystemService extends KService, KSystemService<App,Account,User,File> {
	public static final String SERVICE_PATH = "rpc/SystemService";
}
