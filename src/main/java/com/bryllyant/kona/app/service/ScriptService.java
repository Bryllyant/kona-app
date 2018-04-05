/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Script;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import javax.script.Bindings;
import javax.script.ScriptException;
import java.util.Map;

public interface ScriptService extends KService, KEntityService<Script> {
	String SERVICE_PATH = "rpc/ScriptService";

    Script fetchBySlug(String slug);

    Object eval(Script script) throws ScriptException;

    Object eval(Script script, Bindings binding) throws ScriptException;

    Object eval(Script script, Map<String,Object> binding) throws ScriptException;
}
