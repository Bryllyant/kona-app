/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.EntityNameRule;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface EntityNameRuleService extends KService, KEntityService<EntityNameRule> {
	String SERVICE_PATH = "rpc/EntityNameRuleService";

    List<EntityNameRule> fetchAll();

    EntityNameRule fetchByPattern(String pattern);

    EntityNameRule fetchForName(String name);

    boolean isReserved(String name);

    boolean isBlackListed(String name);

    boolean isAcceptable(String name);
}
