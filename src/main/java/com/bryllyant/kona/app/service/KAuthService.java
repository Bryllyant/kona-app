/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KToken;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.remote.service.KService;
import com.bryllyant.kona.remote.service.KServiceClient;


public interface KAuthService<U extends KUser, T extends KToken> extends KService {
    T login(String clientId, String username, String password) throws KAuthException;
    
    T login(String clientId, U user);
    
    T login(T token, KServiceClient client) throws KAuthException;
    
    T login(String accessToken, KServiceClient client) throws KAuthException;
    
    void logout(U user);
    
    void logout(T token);
    
    void logout(String accessToken);
    
    /** Return the token type issued when a user logs in.
     *  Assumes an external reference to TokenType object that includes references to BASIC, BEARER, etc. */ 
    T.Type getLoginTokenType();
    

    
    /**
     * Create a token without logging the user in.
     * 
     * This method does not check if the user already has an active token associated with this clientId.
     */
	public T createToken(U user, String clientId, String scope);

	public T createToken(U user, String clientId);
}
