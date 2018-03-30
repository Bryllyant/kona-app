/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KAuthRole;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;


public interface KAuthRoleService<AUTH_ROLE extends KAuthRole> extends KService, KEntityService<AUTH_ROLE> {

    AUTH_ROLE fetchByName(String name);

}
