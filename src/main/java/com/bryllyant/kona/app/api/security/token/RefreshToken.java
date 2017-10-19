/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.api.security.token;

import org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken;

import java.util.Date;


/**
 * Encapsulates an OAuth 2.0 access token.
 */
public class RefreshToken extends  DefaultExpiringOAuth2RefreshToken {
    
	private static final long serialVersionUID = 1L;

	public RefreshToken(String value, Date expirationDate) {
        super(value, expirationDate);
    }

	

}
