/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.Push;
import com.bryllyant.kona.app.entity.Sms;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.remote.service.KService;

public interface SystemService extends KService, KSystemService<App,Account,User,File,Email,Sms,Push> {
	String SERVICE_PATH = "rpc/SystemService";
}
