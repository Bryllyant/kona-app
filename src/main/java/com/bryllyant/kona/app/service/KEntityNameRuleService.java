/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import java.util.List;

import com.bryllyant.kona.app.entity.KEntityNameRule;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;


/**
 * The client side stub for the RPC service.
 */
public interface KEntityNameRuleService<E extends KEntityNameRule> extends KService, KEntityService<E> {
    public static final String SERVICE_PATH = "rpc/kona/EntityNameRuleService";
    
    public List<E> fetchAll();

    public E fetchByUid(String uid);

    public E fetchByPattern(String pattern);
    
    public E fetchForName(String name);
    
    public boolean isReserved(String name);
    
    public boolean isBlackListed(String name);
    
    public boolean isAcceptable(String name);
    
}
