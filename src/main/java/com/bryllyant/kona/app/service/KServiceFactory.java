/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

/**
 * 
 */
public interface KServiceFactory {

    @SuppressWarnings("rawtypes")
	public KAuthService getAuthService();
    
    @SuppressWarnings("rawtypes")
    public KUserService getUserService();
    
    @SuppressWarnings("rawtypes")
    public KFileService getFileService();
}
