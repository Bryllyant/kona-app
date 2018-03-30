/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KPolicy;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface KPolicyService<POLICY extends KPolicy> extends KService, KEntityService<POLICY> {

    POLICY fetchByUid(String uid);

    List<POLICY> fetchByType(KPolicy.Type type);
    
    POLICY fetchByTypeAndVersion(KPolicy.Type type, Integer version);

    POLICY fetchActive(KPolicy.Type type);
}
