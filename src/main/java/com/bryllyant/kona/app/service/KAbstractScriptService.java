/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KScript;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public abstract class KAbstractScriptService<
        SCRIPT extends KScript,
        SCRIPT_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<SCRIPT, SCRIPT_EXAMPLE>>
        extends KAbstractService<SCRIPT,SCRIPT_EXAMPLE,MAPPER>
        implements KScriptService<SCRIPT> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractScriptService.class);

	private Map<SCRIPT.Language,ScriptEngine> scriptEngineMap = new HashMap<>();
	private ScriptEngineManager scriptEngineManager = new ScriptEngineManager();


    @Override
	public void validate(SCRIPT script) {
		if (script.getCreatedDate() == null) {
			script.setCreatedDate(new Date());
		}

        script.setUpdatedDate(new Date());

		if (script.getUid() == null) {
		    script.setUid(uuid());
        }
	}


    @Override
    public SCRIPT fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

	@Override
	public SCRIPT fetchBySlug(String slug) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("slug", slug);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
    

	private ScriptEngine getScriptEngine(SCRIPT.Language language) {
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
    public Object eval(SCRIPT script) throws ScriptException {
        return eval(script, null);
    }

    @Override
    public Object eval(SCRIPT script, Map<String,Object> map) throws ScriptException {
        Bindings bindings = null;

        if (map != null) {
            bindings = new SimpleBindings(map);
        }

        return eval(script, bindings);
    }
    
    @Override
    public Object eval(SCRIPT script, Bindings bindings) throws ScriptException {
        ScriptEngine engine = getScriptEngine(script.getLanguage());
        CompiledScript compiledScript = ((Compilable) engine).compile(script.getBody());
        return compiledScript.eval(bindings);
    }
    


}
