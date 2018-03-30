/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KAuthPriv;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;


public interface KAuthPrivService<AUTH_PRIV extends KAuthPriv> extends KService, KEntityService<AUTH_PRIV> {

    AUTH_PRIV fetchByName(String uid);
}
