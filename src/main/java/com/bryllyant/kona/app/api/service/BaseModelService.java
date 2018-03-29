package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(BaseModelService.class);

    @Autowired
    private AppUtil util;


    protected Map<String, Object> toMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

}


