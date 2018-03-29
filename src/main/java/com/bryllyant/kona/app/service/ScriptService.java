/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Script;
import com.bryllyant.kona.remote.service.KService;

public interface ScriptService
        extends KService, KScriptService<Script> {

	String SERVICE_PATH = "rpc/ScriptService";
}
