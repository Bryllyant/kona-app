/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KRemoteService;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;


public interface KRemoteServiceService<REMOTE_SERVICE extends KRemoteService> 
        extends KService, KEntityService<REMOTE_SERVICE> {

    public REMOTE_SERVICE fetchByUid(String uid);
    public REMOTE_SERVICE fetchBySlug(String slug);
}
