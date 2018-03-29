/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.ScriptMapper;
import com.bryllyant.kona.app.entity.Script;
import com.bryllyant.kona.app.entity.ScriptExample;
import com.bryllyant.kona.app.service.KAbstractScriptService;
import com.bryllyant.kona.app.service.ScriptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(ScriptService.SERVICE_PATH)
public class ScriptServiceImpl
        extends KAbstractScriptService<
        Script,
        ScriptExample,
        ScriptMapper>
        implements ScriptService {

    private static Logger logger = LoggerFactory.getLogger(ScriptServiceImpl.class);

    @Autowired
    private ScriptMapper scriptMapper;


    @Override @SuppressWarnings("unchecked")
    protected ScriptMapper getMapper() {
        return scriptMapper;
    }

}
