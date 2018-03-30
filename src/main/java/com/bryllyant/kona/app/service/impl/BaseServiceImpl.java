/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.remote.service.KBaseServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseServiceImpl {
	private static Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

	private static Map<String,KBaseServiceFactory> serviceMap = new HashMap<String,KBaseServiceFactory>();

	@Autowired
	private KConfig config;

	public <T> T getService(String module, String name) {
		return getServiceFactory(module).getService(name);
	}

	public KBaseServiceFactory getServiceFactory(String module) {
		KBaseServiceFactory factory = null;
		factory = serviceMap.get(module);
		if (factory == null) {
			String baseUrl = config.getString("services." + module + ".baseUrl");
			String basePackage = config.getString("services." + module + ".basePackage");
			factory = KBaseServiceFactory.getInstance(baseUrl, basePackage);
			serviceMap.put(module, factory);
		}
		return factory;
	}
}
