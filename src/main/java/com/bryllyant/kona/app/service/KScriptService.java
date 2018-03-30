/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KScript;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import javax.script.Bindings;
import javax.script.ScriptException;
import java.util.Map;

public interface KScriptService<SCRIPT extends KScript>
        extends KService, KEntityService<SCRIPT> {

    SCRIPT fetchByUid(String uid);

    SCRIPT fetchBySlug(String slug);

    Object eval(SCRIPT script) throws ScriptException;

    Object eval(SCRIPT script, Bindings binding) throws ScriptException;

    Object eval(SCRIPT script, Map<String,Object> binding) throws ScriptException;
}
