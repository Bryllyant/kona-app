/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.ScriptMapper;
import com.bryllyant.kona.app.entity.Script;
import com.bryllyant.kona.app.entity.ScriptExample;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.service.KServiceException;
import com.bryllyant.kona.app.service.ScriptService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import com.bryllyant.kona.util.KJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service(ScriptService.SERVICE_PATH)
public class ScriptServiceImpl
        extends KAbstractService<Script,ScriptExample,ScriptMapper>
        implements ScriptService {

    private static Logger logger = LoggerFactory.getLogger(ScriptServiceImpl.class);

    private Map<Script.Language,ScriptEngine> scriptEngineMap = new HashMap<>();

    private ScriptEngineManager scriptEngineManager = new ScriptEngineManager();

    @Autowired
    private ScriptMapper scriptMapper;


    @Override @SuppressWarnings("unchecked")
    protected ScriptMapper getMapper() {
        return scriptMapper;
    }


    @Override
    public void validate(Script script) {
        if (script.getCreatedDate() == null) {
            script.setCreatedDate(new Date());
        }

        script.setUpdatedDate(new Date());

        if (script.getUid() == null) {
            script.setUid(uuid());
        }

        if (script.getName() == null) {
            script.setName(script.getUid());
        }

        script.setSlug(KInflector.getInstance().slug(script.getName()));
    }

    @Override
    public Script fetchBySlug(String slug) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    private ScriptEngine getScriptEngine(Script.Language language) {
        if (!scriptEngineMap.containsKey(language)) {
            ScriptEngine engine = scriptEngineManager.getEngineByName(language.name().toLowerCase());

            if (engine == null) {
                throw new KServiceException("ScriptEngine not found for language: " + language
                        + "\nSupported Engines:\n" + KJsonUtil.toJson(getSupportedScriptEngines(), 5000));
            }

            scriptEngineMap.put(language, engine);
        }

        return scriptEngineMap.get(language);
    }

    private List<String> getSupportedScriptEngines() {
        List<String> result = new ArrayList<>();

        for (ScriptEngineFactory factory : scriptEngineManager.getEngineFactories()) {
            String engine = "Engine: " + factory.getEngineName() + " (" + factory.getEngineVersion() + ") "
                    + "Language: " + factory.getLanguageName() + " (" + factory.getLanguageVersion() + ")";

            result.add(engine);
        }

        return result;
    }


    @Override
    public Object eval(Script script) throws ScriptException {
        return eval(script, null);
    }

    @Override
    public Object eval(Script script, Map<String,Object> map) throws ScriptException {
        Bindings bindings = null;

        if (map != null) {
            bindings = new SimpleBindings(map);
        }

        return eval(script, bindings);
    }

    @Override
    public Object eval(Script script, Bindings bindings) throws ScriptException {
        ScriptEngine engine = getScriptEngine(script.getLanguage());
        CompiledScript compiledScript = ((Compilable) engine).compile(script.getBody());
        return compiledScript.eval(bindings);
    }

    @Override
    public Script create(String name, String body, Script.Language language, Script.ReturnType returnType) {
        Script script = new Script();

        script.setName(name);
        script.setBody(body);
        script.setReturnType(returnType);
        script.setLanguage(language);
        script.setEnabled(true);

        return save(script);
    }

}
